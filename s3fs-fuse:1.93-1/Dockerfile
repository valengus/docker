FROM ghcr.io/valengus/oraclelinux:8

RUN dnf install -y s3fs-fuse; \
    dnf clean all

CMD ["/usr/sbin/init"]
