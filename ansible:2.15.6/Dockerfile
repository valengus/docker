FROM ghcr.io/valengus/oraclelinux:9

RUN dnf install -y python3 python3-pip && \
    pip install pip --upgrade && \
    pip install ansible-core==2.15.6 && \
    pip install ansible && \
    pip install ansible-compat==3.0.2 && \
    pip install cryptography==41.0.1 && \
    pip install jinja2==3.1.2 && \
    pip install jmespath==1.0.1 && \
    ansible-galaxy collection install community.crypto -p /usr/share/ansible/collections && \
    ansible --version && \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["sleep infinity"]
