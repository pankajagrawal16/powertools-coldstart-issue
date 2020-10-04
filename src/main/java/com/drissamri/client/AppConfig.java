package com.drissamri.client;

import com.drissamri.client.service.ClientService;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.http.crt.AwsCrtAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class AppConfig {
    public static DynamoDbAsyncClient dynamoDbClient() {
        return DynamoDbAsyncClient.builder()
                .httpClientBuilder(AwsCrtAsyncHttpClient.builder()
                        .maxConcurrency(50))
                .region(Region.of(System.getenv("AWS_REGION")))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();

    }

    public static ClientService clientService() {
        return new ClientService(dynamoDbClient());
    }
}
