package nb_wallet_api.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
@Data
public abstract class ApiRequest {
    protected String url;
    protected RequestSpecification requestSpecification;
    protected Response response;
    private static String SLASH = "/";
    private String customerToken;

    public ApiRequest(String url) {
        this.url = url;
        this.requestSpecification = given()
                .baseUri(url)
                .accept("*/*");
        requestSpecification.log().all();
    }

    protected void logResponse() {
        log.warn("Response is:");
        log.warn(getResponse().getBody().asPrettyString());
        log.warn("Status code is {}", getResponse().getStatusCode());
    }

    protected static String getEndpoint(String... endpoints) {
        StringBuilder endPoint = new StringBuilder();
        for (String arg : endpoints) {
            endPoint.append(arg).append(SLASH);
        }
        return endPoint.substring(0, endPoint.length() - 1);
    }

    public String formatParameter(HashMap<String, String> parameters) {
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            query.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        return query.deleteCharAt(query.length() - 1).toString();
    }

    protected Response get(String endPoint) {
        log.info("performed GET {}", endPoint);
        this.response = given()
                .spec(requestSpecification)
                .port(EnvironmentManager.choosePort(endPoint))
                .auth()
                .oauth2(customerToken)
                .get(endPoint);
        logResponse();
        customerToken = response.jsonPath().getString("jwtToken");
        return this.response;
    }

    protected Response get(String endPoint, Map<String, String> params) {
        log.info("Performed GET {}", endPoint);
        log.info("Params is {}", params);
        this.response = given()
                .spec(requestSpecification)
                .formParams(params)
                .get(endPoint);
        logResponse();
        return this.response;
    }


    protected Response post(String endPoint, String body) {
        log.info("Performed post {}", endPoint);
        log.info("Params is {}", body);

        this.response = given()
                .header("Authorization", "Bearer " + customerToken)
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(body)
                .post(endPoint);

        // Log the full response body for debugging
        log.warn("Response body: {}", response.getBody().asString());

        logResponse();
        customerToken = String.valueOf(response.jsonPath());
        return this.response;
    }


    protected void postAddFund(String endPoint, String body) {
        // Create the request specification
        RequestSpecification requestSpec = given()
                .header("Authorization", "Bearer " + customerToken)
                .contentType(ContentType.JSON)
                .body(body);

        // Log request body
        log.info("Request Body: {}", body);

        // Log request headers
        log.info("Request Headers: Authorization=Bearer {}", customerToken);
        log.info("Request Content-Type: application/json");

        // Execute the POST request
        this.response = requestSpec.post(endPoint);

        // Log the full response after the request is executed
        log.warn("Response is:");
        log.warn(getResponse().getBody().asPrettyString());
        log.warn("Response body: {}", getResponse().asString());
        log.warn("Status code is {}", getResponse().getStatusCode());

        // Update the customerToken if necessary
        if (response.getStatusCode() == 200) {
            customerToken = response.jsonPath().getString("jwtToken");
        }
    }



    protected void postAuth(String endPoint, String body) {
        log.info("Performed post {}", endPoint);
        log.info("Body is {}", body);
        this.response = given()
                .spec(requestSpecification)
                .port(EnvironmentManager.choosePort(endPoint))
                .auth()
                .oauth2(customerToken)
                .contentType(ContentType.JSON)
                .body(body)
                .post(endPoint);
        logResponse();
    }

    protected void postWithParams(String endPoint, Map<String, String> params) {
        log.info("Performed GET {}", endPoint);
        log.info("Params is {}", params);
        this.response = given()
                .spec(requestSpecification)
                .port(EnvironmentManager.choosePort(endPoint))
                .formParams(params)
                .post(endPoint);
        logResponse();
    }

}