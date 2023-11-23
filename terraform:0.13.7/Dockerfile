FROM ghcr.io/valengus/ansible:latest

RUN dnf install -y yum-utils ; \
    yum-config-manager --add-repo https://rpm.releases.hashicorp.com/RHEL/hashicorp.repo ; \
    dnf -y install packer terraform-0.13.7 ; \
    dnf clean all

CMD ["/usr/sbin/init"]