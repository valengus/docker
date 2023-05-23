FROM ghcr.io/valengus/python:3.9

RUN python3 -m pip install pywinrm; \
    ansible-galaxy collection install community.crypto -p /usr/share/ansible/collections ; \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]
