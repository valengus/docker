FROM ghcr.io/valengus/oraclelinux:9

RUN dnf install -y python3 python3-pip && \
    pip install pip --upgrade && \
    pip install ansible-core>=2.16.6 && \
    pip install ansible && \
    pip install ansible-compat && \
    pip install cryptography && \
    pip install jinja2 && \
    pip install jmespath && \
    ansible-galaxy collection install community.crypto -p /usr/share/ansible/collections && \
    ansible --version && \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["sleep infinity"]
