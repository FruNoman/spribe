package com.spribe.test.rest.filters;

import com.spribe.test.utils.Log;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.util.Objects;

public class SpribeLogFilter implements Filter {
    private final String LOG_SEPARATOR = "____________________________________________________________________________\n";
    private final Prettifier prettifier = new Prettifier();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        Response response = filterContext.next(requestSpec, responseSpec);
        logRequest(requestSpec);
        logResponse(response);
        return response;
    }

    private void logRequest(FilterableRequestSpecification requestSpec) {
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(LOG_SEPARATOR);
        requestBuilder.append("REQUEST: ");
        requestBuilder.append(requestSpec.getMethod()).append("\n");
        requestBuilder.append(requestSpec.getURI()).append("\n");
        requestBuilder.append(requestSpec.getHeaders().asList()).append("\n");
        if (Objects.nonNull(requestSpec.getBody())) {
            String body = prettifier.getPrettifiedBodyIfPossible(requestSpec);
            requestBuilder.append(body);
        }
        Log.info(requestBuilder.toString());
    }

    private void logResponse(Response response) {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append(LOG_SEPARATOR);
        responseBuilder.append("RESPONSE: ");
        responseBuilder.append(response.getStatusCode());
        responseBuilder.append("\n");
        responseBuilder.append(response.getHeaders().asList());
        responseBuilder.append("\n");
        String responseBody = prettifier.getPrettifiedBodyIfPossible(response, response.getBody());
        responseBuilder.append(responseBody);
        Log.info(responseBuilder.toString());
    }
}
