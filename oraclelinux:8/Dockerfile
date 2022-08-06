FROM oraclelinux:8

RUN dnf install -y epel-release ; \
    dnf install -y tini ; \
    dnf clean all

CMD ["/usr/sbin/init"]