package com.drissamri.client.config;

import com.drissamri.client.service.ClientService;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.http.crt.AwsCrtAsyncHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class AppConfig {
    public static DynamoDbAsyncClient dynamoDbClient() {
        return DynamoDbAsyncClient.builder()
                .httpClientBuilder(AwsCrtAsyncHttpClient.builder().maxConcurrency(50))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }

    public static ClientService clientService() {
        return new ClientService(dynamoDbClient());
    }
}
