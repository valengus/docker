```bash
packer init docker.pkr.hcl

packer build -var-file="vars/oraclelinux9.pkrvars.hcl" -only='*.docker.build' docker.pkr.hcl
packer build -var-file="vars/jdk17.pkrvars.hcl"        -only='*.docker.build' docker.pkr.hcl
packer build -var-file="vars/jenkins.pkrvars.hcl"      -only='*.docker.build' docker.pkr.hcl

docker-compose up -d


packer build -var-file="vars/oraclelinux9.pkrvars.hcl" -only='docker.push' docker.pkr.hcl

```
