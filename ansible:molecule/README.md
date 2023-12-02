

```bash
docker run --name ansible_molecule --rm -d \
-v /var/run/docker.sock:/var/run/docker.sock \
-v $(pwd):/molecule ghcr.io/valengus/ansible:molecule
```

```bash
docker exec -it ansible_molecule bash
molecule test --all
```
