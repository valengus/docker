base_image = "docker.io/library/oraclelinux:9"
image_name = "oraclelinux9"
tags       = ["latest"]
changes    = [
  "ENV LANG=\"C.UTF-8\"",
  "CMD [\"/usr/bin/bash\"]",
]
