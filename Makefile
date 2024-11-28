prepare:
	packer init oraclelinux9.pkr.hcl

build-oraclelinux9:
	packer build oraclelinux9.pkr.hcl

build-%:
	packer build -var-file="vars/${*}.pkrvars.hcl" oraclelinux9.pkr.hcl
