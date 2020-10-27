package com.demo.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "user_provider_5")
class TemplateContractTestJUnit5 {

    /**
     * Create a contract with:
     * + State: The provider has at least one user
     * + Response: is an array, looks like
     * [
     *      {
     *          "username": "test",
     *          "firstname": "Conte"
     *      },
     *      {
     *          "username": "test",
     *          "firstname": "Conte"
     *      }
     * ]
     */
    @Pact(provider = "user_provider_5", consumer = "DS - Get List Users")
    public RequestResponsePact successfulResponseContract(PactDslWithProvider builder) {
        return builder
            .given("The provider has at least one user")
            .uponReceiving("Get list users") // Description of the request that is expected to be received
            .path("/api/v1/users")
            .method("GET")
            .willRespondWith()
            .status(200)
            .body(PactDslJsonArray.arrayEachLike()
                .stringType("username")
                .stringType("firstname")
                .closeObject()
            )
            .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "successfulResponseContract")
    public void successfulResponseContractTest(MockServer mockServer) {
        RestAssured
            .given().log().all()
            .baseUri(mockServer.getUrl())
            .get("/api/v1/users")
            .then()
            .statusCode(200)
            .header("Content-Type", "application/json; charset=UTF-8");
        RestAssured.reset();
    }
}
