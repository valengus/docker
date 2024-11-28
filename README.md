```bash
docker rm -vf $(docker ps -aq) ; docker rmi -f $(docker images -aq)

make prepare

make build-oraclelinux9
make build-jdk17
make build-jenkins

docker-compose stop ; docker-compose rm -f ; docker-compose up -d
```
