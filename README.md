# Service Oriented Architecture. Laboratory Work 2

## Second service

### Variant 3005.11

### How to deploy

#### Environment settings
| Variable      | Required           | Example                 |
|---------------|--------------------|-------------------------|
| KEYSTORE_PATH | :white_check_mark: | `/path/to/keystore.jks` |
| KEYSTORE_PASS | :white_check_mark: | `password`              |
| STORAGE_SERVICE_URL | :white_check_mark: | `localhost:8080` |

#### Using Payara
1. `cd /path/to/payara/bin`
2. `asadmin start-domain`
3. build war file with `mvn clean install`
4. Open payara admin console (Usually `localhsot:4848`)
5. Choose applications. Click deploy and choose your war file
6. Go to `Configuration` section in sidebar
7. Choose `server-config`
8. Choose `JVM Settings`
9. Choose `JVM Options` tab
10. Add options to env variables with prefix `-D`. For example to set `KEYSTORE_PATH=/path/to` you need to add
parameter with value `-DKEYSTORE_PATH=/path/to`
11. Restart payara (you can do it with a command `asadmin restart-domain`)

#### Setup Payara SSL
4. Open payara admin console (Usually `localhsot:4848`)
5. Choose applications. Click deploy and choose your war file
6. Go to `Configuration` section in sidebar
7. Choose `server-config`
8. Choose `Admin Service`
9. Choose `SSL` tab
10. Copy your keystore file to `/path/to/payara/config/` 
11. Enter keystore alias to `Certificate NickName:`
12. Enter keystore filename to `Key Store:`
13. `asadmin restart-domain`
14. If your master password is different with keystore pass (default master pass is `changeit`), you need to change master password
    1. Run `asadmin stop-domain`
    2. Run `asadmin change-master-password`
    2. Run `asadmin start-domain`

### Новый сервис должен располагаться на URL /route и реализовывать следующие операции:
```
/calculate/length/{id1}/{id2} : рассчитать длину маршрута из города с id=id1 в город с id=id2
/calculate/to-max-populated : рассчитать длину маршрута от точки с координатами (0,0,0) до города с максимальным населением
```