FROM ghcr.io/valengus/oraclelinux:8

ENV container=docker

RUN yum reinstall -y systemd && \
    rm -f /lib/systemd/system/multi-user.target.wants/* && \
    rm -f /etc/systemd/system/*.wants/* && \
    rm -f /lib/systemd/system/local-fs.target.wants/* && \
    rm -f /lib/systemd/system/sockets.target.wants/*udev* && \
    rm -f /lib/systemd/system/sockets.target.wants/*initctl* && \
    rm -f /lib/systemd/system/basic.target.wants/* && \
    rm -f /lib/systemd/system/anaconda.target.wants/*

RUN yum -y install rpm dnf-plugins-core  && \
    yum -y config-manager --set-enabled powertools  && \
    yum -y install initscripts sudo which hostname libyaml-devel python3 python3-devel python3-dnf python3-pip python3-pyyaml iproute && \
    yum clean all

VOLUME [/sys/fs/cgroup]

CMD ["/usr/lib/systemd/systemd"]
