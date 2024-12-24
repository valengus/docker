```bash
packer init docker.pkr.hcl

packer build -var-file="vars/oraclelinux9.pkrvars.hcl" docker.pkr.hcl
packer build -var-file="vars/jdk17.pkrvars.hcl"        docker.pkr.hcl
packer build -var-file="vars/jenkins.pkrvars.hcl"      docker.pkr.hcl

docker-compose up -d
```
