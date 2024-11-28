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



source "docker" "base_image" {
  image       = var.base_image
  run_command = [ "-dit", "--name", "packer_build_${var.image_name}", "{{.Image}}", "/bin/sh" ]
  commit      = true
  pull        = false
  changes     = var.changes
}

build {

  name    = var.image_name
  sources = [ "source.docker.base_image" ]

  provisioner "ansible" {
    playbook_file   = "ansible/${var.image_name}.yml"
    user            = "root"
    extra_arguments = [ "--extra-vars", "ansible_host=packer_build_${var.image_name} ansible_connection=docker", ]
  }

  provisioner "ansible" {
    playbook_file   = "ansible/common/cleanup.yml"
    user            = "root"
    extra_arguments = [ "--extra-vars", "ansible_host=packer_build_${var.image_name} ansible_connection=docker", ]
  }

  post-processor "docker-tag" {
    repository = "local/${var.image_name}"
    tags       = var.tags
  }

}
