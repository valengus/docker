FROM ghcr.io/valengus/oraclelinux:8

RUN dnf install -y https://rpms.remirepo.net/enterprise/remi-release-8.rpm ; \
    dnf module enable php:remi-8.1 -y ; \
    dnf install -y php php-cli php-mysqlnd \
    php-zip php-devel php-gd php-mbstring php-curl \ 
    php-xml php-pear php-bcmath php-json ; \
    dnf clean all ; \
    curl -o /usr/src/composer-setup.php https://getcomposer.org/installer ; \
    php /usr/src/composer-setup.php --install-dir=/usr/local/bin --filename=composer

CMD ["/usr/sbin/init"]