# repl-issue

## Steps to Reproduce

### Showing that it works

1. Works with only clojure
2. Works with only docker
3. Does not work with dokku??

#### 1. Clojure

💡 make sure you clojure installed: `brew install clojure/tools/clojure`

then:

```shell
clojure -X dokku-clojure.repl-issue/run
```

in another shell:

```shell
➜ curl localhost:4000
ok 4444%             
```

server works! Now the repl:

```shell
➜ nc localhost 4001
user=> (+ 1 1)
2
```
Server and repl work! Kill the clojure process.

#### 2. Docker

```shell
➜ docker build . --tag dokku_clojure:repl_issue
```
```shell
➜ docker run -it -p 4000:4000 -p 4001:4001 dokku_clojure:repl_issue
```

in another shell:

```shell
➜ curl localhost:4000
ok 4444%             
```

server works! Now the repl:

```shell
➜ nc localhost 4001
user=> (+ 1 1)
2
```

It works! Identical to Clojure.

#### 3. Dokku

This is more involved. Follow these initial steps to deploy this repo
as an application: https://dokku.com/docs/deployment/application-deployment/

I used dokku from the digitalocean markeplace: https://marketplace.digitalocean.com/apps/dokku

ssh into the instance: `✗ ssh root@<ip-address>`
After I had the app running I needed to unblock ports in `ufw`:

```shell
ufw allow 4000
ufw allow 4001
```

Then map these ports using dokku:

```shell
dokku proxy:ports-add rascal http:4000:4000
dokku proxy:ports-add rascal http:4001:4001
```

test it:

```shell
➜ curl <ip-address>:4000/ 
ok%                
```

works!

```shell
➜ nc <ip-address> 4001
```

this just hangs.... 

**why does the repl not work? :/**
