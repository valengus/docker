

```bash
docker run --name ansible_molecule --rm -d \
-v /var/run/docker.sock:/var/run/docker.sock \
-v $(pwd):/molecule ghcr.io/valengus/ansible:molecule
```

```bash
docker run --rm -v /var/run/docker.sock:/var/run/docker.sock -v $(pwd):/molecule ghcr.io/valengus/ansible:molecule molecule test --all
```
