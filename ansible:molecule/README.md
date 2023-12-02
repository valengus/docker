

```bash
docker run --rm -d --cgroupns=host --privileged \
--tmpfs /run --tmpfs /tmp -v /sys/fs/cgroup:/sys/fs/cgroup \
--name ansible -v $(pwd):/molecule ghcr.io/valengus/ansible:molecule \
/usr/lib/systemd/systemd
```
