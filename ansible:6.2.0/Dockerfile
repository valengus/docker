FROM ghcr.io/valengus/python:3.9


RUN dnf install ansible sshpass; \
    ansible-galaxy collection install community.crypto -p /usr/share/ansible/collections ; \
    ansible --version ; \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]
