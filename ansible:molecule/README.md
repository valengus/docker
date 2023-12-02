

```bash
docker run --name molecule --rm -it \
-v /var/run/docker.sock:/var/run/docker.sock \
-v $(pwd):/molecule -v ~/.ssh/id_rsa:/root/.ssh/id_rsa \
ghcr.io/valengus/ansible:molecule bash

molecule test all
```
