### Build
```bash
packer init docker.pkr.hcl

packer build -var-file="vars/oraclelinux9.pkrvars.hcl" -only='*.docker.build' docker.pkr.hcl
packer build -var-file="vars/jdk17.pkrvars.hcl" -only='*.docker.build' docker.pkr.hcl
packer build -var-file="vars/jenkins.pkrvars.hcl" -only='*.docker.build' docker.pkr.hcl
```

### Up local test env
```bash
docker-compose up -d
```

### Test
```bash
packer build -var-file="vars/oraclelinux9.pkrvars.hcl" -only='*.docker.test' docker.pkr.hcl
```


### Push
```bash
packer build -var-file="vars/oraclelinux9.pkrvars.hcl" -only='docker.push' docker.pkr.hcl
```