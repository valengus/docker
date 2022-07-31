FROM oraclelinux:9

RUN dnf install -y epel-release yum-utils ; \
    dnf install -y python3 ansible git ; \
    yum-config-manager --add-repo https://rpm.releases.hashicorp.com/RHEL/hashicorp.repo ; \
    dnf -y install terraform-0.13.7 ; \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]