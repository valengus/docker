packer {
  required_plugins {
    docker = {
      version = ">= 1.0.8"
      source = "github.com/hashicorp/docker"
    }
    ansible = {
      version = "~> 1"
      source = "github.com/hashicorp/ansible"
    }
  }
}

locals {
  packerstarttime = formatdate("YYYYMMDD", timestamp())
}

variable "base_image" {
  type    = string
  default = "docker.io/library/oraclelinux:9"
}

variable "image_name" {
  type    = string
  default = "oraclelinux9"
}

variable "tags" {
  type    = list(string)
  default = ["latest"]
}

variable "changes" {
  type    = list(string)
  default = [
    "ENV LANG=\"C.UTF-8\"",
    "CMD [\"/usr/bin/bash\"]",
  ]
}

variable "docker_registry" {
  type    = string
  default = "localhost:5000"
}

# BUILD
source "docker" "build" {
  image       = var.base_image
  run_command = [ "-dit", "--name", "packer-build-${var.image_name}", "{{.Image}}", "/bin/sh" ]
  commit      = true
  pull        = false
  changes     = var.changes
}

build {

  name    = var.image_name
  sources = [ "source.docker.build" ]

  provisioner "ansible" {
    playbook_file   = "ansible/${var.image_name}.yml"
    user            = "root"
    extra_arguments = [ "--extra-vars", "ansible_host=packer-build-${var.image_name} ansible_connection=docker", ]
  }

  provisioner "ansible" {
    playbook_file   = "ansible/common/cleanup.yml"
    user            = "root"
    extra_arguments = [ "--extra-vars", "ansible_host=packer-build-${var.image_name} ansible_connection=docker", ]
  }

  post-processors {

    post-processor "docker-tag" {
      repository = "local/${var.image_name}"
      tags       = concat(var.tags, [local.packerstarttime,])
    }

  }

}



# TEST
source "docker" "test" {
  image       = "local/${var.image_name}"
  run_command = [ "-dit", "--name", "packer-test-${var.image_name}", "{{.Image}}", "/bin/sh" ]
  discard     = true
  pull        = false
}

build {

  name    = var.image_name
  sources = [ "source.docker.test" ]

  provisioner "ansible" {
    playbook_file   = fileexists("ansible/test/${var.image_name}.yml") ? "ansible/test/${var.image_name}.yml" : "ansible/test/common.yml"
    user            = "root"
    extra_arguments = [ "--extra-vars", "ansible_host=packer-test-${var.image_name} ansible_connection=docker", ]
  }

}


# PUSH
source "docker" "push" {
  image       = "local/${var.image_name}"
  commit      = true
  pull        = false
}

build {
  sources = [ "source.docker.push" ]

  post-processors {

    post-processor "docker-tag" {
      repository          = "${var.docker_registry}/${var.image_name}"
      keep_input_artifact = true
      tags                = concat(var.tags, [local.packerstarttime,])
    }

    post-processor "docker-push" {
      login = false
    }

  }

}
