all: prepare clean buildLocal

clean:
	docker image prune -af

prepare:
	packer init oraclelinux9.pkr.hcl

buildLocal:
	packer build oraclelinux9.pkr.hcl
	packer build -var-file="vars/python39.pkrvars.hcl"   oraclelinux9.pkr.hcl
	packer build -var-file="vars/jdk17.pkrvars.hcl"      oraclelinux9.pkr.hcl
	packer build -var-file="vars/jenkins.pkrvars.hcl"    oraclelinux9.pkr.hcl
	packer build -var-file="vars/ansible215.pkrvars.hcl" oraclelinux9.pkr.hcl
	packer build -var-file="vars/docker.pkrvars.hcl"     oraclelinux9.pkr.hcl
	packer build -var-file="vars/packer.pkrvars.hcl"     oraclelinux9.pkr.hcl

build-%:
	packer build -var-file="vars/${*}.pkrvars.hcl"       oraclelinux9.pkr.hcl
