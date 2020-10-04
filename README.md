```
mvn clean package 
```
```
sls deploy
```

```
lumigo-cli measure-lambda-cold-starts -p dev -n client-service-dev-create-client -r eu-central-1 -e '{ "body": { "name": "play sports 1" } }'
```

lambda-logging (baseline) 
```
 {
      "functionName": "client-service-dev-create-client",
      "memorySize": 3008,
      "coldStarts": 40,
      "min": 1142.29,
      "p25": 1192.22,
      "median": 1213.9,
      "p75": 1223.73,
      "p95": 1261.69,
      "max": 1276.78,
      "stddev": 29.0004
}
```
powertools-logging
```
{
      "functionName": "client-service-dev-create-client",
      "memorySize": 3008,
      "coldStarts": 71,
      "min": 1787.6,
      "p25": 1952.06,
      "median": 1981.9,
      "p75": 2004.21,
      "p95": 2063.38,
      "max": 2149.03,
      "stddev": 53.575
}
```