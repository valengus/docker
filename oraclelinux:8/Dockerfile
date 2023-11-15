FROM oraclelinux:8

RUN dnf install -y epel-release ; \
    dnf install -y tini git s3fs-fuse; \
    dnf clean all ; \
    echo '${MINIO_LOGIN}:${MINIO_PASS}' > /etc/passwd-s3fs ; \
    chmod 0400 /etc/passwd-s3fs

CMD ["/usr/sbin/init"]
