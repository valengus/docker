FROM ghcr.io/valengus/oraclelinux:8

RUN dnf install -y python39 python39-pip git sshpass ; \
    pip3 install pip --upgrade ; \
    python3 -m pip install ansible==6.2.0 ; \
    ansible-galaxy collection install community.crypto ; \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]