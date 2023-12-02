FROM ghcr.io/valengus/oraclelinux:9

RUN yum install -y python3 python3-pip && \
    pip install pip --upgrade && \
    pip install ansible-core==2.15.6 && \
    pip install ansible && \
    pip install molecule>=4.0.0 && \
    pip install molecule-docker>=2.0.0 && \
    pip install yamllint>=1.27.1 && \
    pip install flake8>=5.0.3 && \
    pip install ansible-lint>=6.4.0 && \
    dnf config-manager --add-repo=https://download.docker.com/linux/centos/docker-ce.repo && \ 
    dnf install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin && \
    systemctl enable docker && \
    dnf clean all

COPY ansible.cfg /etc/ansible/ansible.cfg

CMD ["/usr/sbin/init"]
