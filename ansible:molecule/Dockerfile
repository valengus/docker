FROM ghcr.io/valengus/ansible:2.15.6

RUN pip install molecule>=4.0.0 && \
    pip install molecule-docker>=2.0.0 && \
    pip install yamllint>=1.27.1 && \
    pip install flake8>=5.0.3 && \
    pip install ansible-lint>=6.4.0 && \
    dnf config-manager --add-repo=https://download.docker.com/linux/centos/docker-ce.repo && \ 
    dnf install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin && \
    mkdir /molecule  && \
    dnf clean all

WORKDIR /molecule

CMD ["/usr/bin/sleep infinity"]
