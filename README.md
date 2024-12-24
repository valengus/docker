### BUILD
```bash
packer init docker.pkr.hcl

packer build -var-file="vars/oraclelinux9.pkrvars.hcl" -only='*.docker.build' docker.pkr.hcl
packer build -var-file="vars/jdk17.pkrvars.hcl"        -only='*.docker.build' docker.pkr.hcl
packer build -var-file="vars/jenkins.pkrvars.hcl"      -only='*.docker.build' docker.pkr.hcl
```
### UP LOCAL TEST ENV
```bash
docker-compose up -d
```

### PUSH
```bash
packer build -var-file="vars/oraclelinux9.pkrvars.hcl" -only='docker.push' docker.pkr.hcl
```