FROM ghcr.io/valengus/python:3.9


RUN pip3 install ansible==2.7.8 ; \
    ansible-galaxy collection install community.crypto -p /usr/share/ansible/collections ; \
    ansible --version

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]
