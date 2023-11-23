FROM ghcr.io/valengus/python:3.9

RUN pip3 install ansible ; \
    pip3 install ansible-core ; \
    ansible-galaxy collection install community.crypto -p /usr/share/ansible/collections ; \
    ansible --version

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]
