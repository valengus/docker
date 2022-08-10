FROM ghcr.io/valengus/ansible:6.2.0

RUN dnf install -y yum-utils ; \
    yum-config-manager --add-repo https://rpm.releases.hashicorp.com/RHEL/hashicorp.repo ; \
    dnf -y install terraform-0.13.7 ; \
    dnf clean all

CMD ["/usr/sbin/init"]