FROM ghcr.io/valengus/oraclelinux:8

RUN dnf install -y python3 git sshpass; \
    dnf install -y python3-pip; \
    dnf clean all

RUN pip3 install --upgrade pip; \
    pip3 install ansible==4.10.0

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]