FROM ghcr.io/valengus/oraclelinux:8

RUN dnf install -y ansible-2.9.27 ; \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]