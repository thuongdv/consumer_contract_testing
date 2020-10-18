package com.demo.contract;

import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.Test;

public class TemplateContractTest {

    @Pact(provider = "Provider - Get List Users", consumer = "DS - Get List Users")
    public RequestResponsePact tc01SuccessfulResponseContract(PactDslWithProvider builder) {
        return builder
            .given("The provider has at least one call")
            .uponReceiving("Get list users") // Description of the request that is expected to be received
            .path("/api/v1/users")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(PactDslJsonArray.arrayEachLike()
                .stringType("username", "test_username")
                .stringType("firstname")
                .closeObject()
            )
            .toPact();
    }

    @Test
    @PactVerification()
    public void tc01SuccessfulResponseTest() {

    }
}
