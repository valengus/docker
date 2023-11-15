FROM oraclelinux:8

RUN dnf install -y epel-release ; \
    dnf install -y tini git s3fs-fuse; \
    dnf clean all

CMD ["/usr/sbin/init"]
