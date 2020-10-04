```
mvn clean package 
```
```
sls deploy
```

```
lumigo-cli measure-lambda-cold-starts -p dev -n client-service-dev-create-client -r eu-central-1 -f src/test/resources/create-client-event.json
```