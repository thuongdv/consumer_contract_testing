package com.demo.contract;

import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import org.junit.Rule;
import org.junit.Test;

public class TemplateContractTest {
    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("user_provider", this);

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
    @Pact(provider = "user_provider", consumer = "DS - Get List Users")
    public RequestResponsePact tc01SuccessfulResponseContract(PactDslWithProvider builder) {
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
    @PactVerification("user_provider")
    public void tc01SuccessfulResponseTest() {
        System.out.println("This is a test");
        RestAssured
            .given()
            .baseUri(mockProvider.getUrl())
            .get("/api/v1/users")
            .then()
            .statusCode(200)
            .header("Content-Type", "application/json; charset=UTF-8");
        RestAssured.reset();
    }
}
