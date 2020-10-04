package com.drissamri.client.core;

import com.amazonaws.xray.interceptors.TracingInterceptor;
import com.drissamri.client.service.ClientService;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.http.crt.AwsCrtAsyncHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class AppConfig {
    public static DynamoDbAsyncClient dynamoDbClient() {
        return DynamoDbAsyncClient.builder()
                .httpClientBuilder(AwsCrtAsyncHttpClient.builder().maxConcurrency(50))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .overrideConfiguration(ClientOverrideConfiguration.builder()
                        .addExecutionInterceptor(new TracingInterceptor())
                        .build())
                .build();
    }

    public static ClientService clientService() {
        return new ClientService(dynamoDbClient());
    }
}
