

```bash
build oraclelinux9.pkr.hcl
packer build -var-file="vars/python39.pkrvars.hcl" oraclelinux9.pkr.hcl
packer build -var-file="vars/jdk17.pkrvars.hcl" oraclelinux9.pkr.hcl
packer build -var-file="vars/jenkins.pkrvars.hcl" oraclelinux9.pkr.hcl
packer build -var-file="vars/ansible215.pkrvars.hcl" oraclelinux9.pkr.hcl
packer build -var-file="vars/docker.pkrvars.hcl" oraclelinux9.pkr.hcl
packer build -var-file="vars/packer.pkrvars.hcl" oraclelinux9.pkr.hcl
```
