```bash
make prepare

make build_basic

docker-compose up -d

docker rm -vf $(docker ps -aq) ; docker rmi -f $(docker images -aq)
```
