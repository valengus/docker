FROM oraclelinux:9

RUN dnf install -y epel-release ; \
    dnf install -y ansible-5.4.0 git ; \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]