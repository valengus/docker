FROM ghcr.io/valengus/python:3.9


RUN dnf install -y python39 python39-pip git sshpass ; \
    pip3 install pip --upgrade ; \
    python3 -m pip install ansible==6.2.0 ; \
    python3 -m pip install pywinrm; \
    ansible-galaxy collection install community.crypto -p /usr/share/ansible/collections ; \
    ansible --version ; \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]
