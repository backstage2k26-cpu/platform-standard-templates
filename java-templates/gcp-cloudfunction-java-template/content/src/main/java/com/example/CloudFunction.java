package com.example;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * GCP Cloud Function: ${{ values.functionName }}
 *
 * This is the entry point for the Cloud Function.
 * Extend this class to implement your business logic.
 */
public class CloudFunction implements HttpFunction {

    private static final Logger logger = Logger.getLogger(CloudFunction.class.getName());
    private static final Gson gson = new Gson();

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {

        // CORS preflight support
        response.appendHeader("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.appendHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            response.appendHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.setStatusCode(204);
            return;
        }

        response.setContentType("application/json");

        try {
            logger.info("[${{ values.functionName }}] Received " + request.getMethod() + " request");

            // Parse request body for POST
            JsonObject requestBody = new JsonObject();
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                requestBody = gson.fromJson(request.getReader(), JsonObject.class);
                if (requestBody == null) {
                    requestBody = new JsonObject();
                }
            }

            // Extract 'name' from query param or request body
            String name = request.getFirstQueryParameter("name")
                    .orElse(requestBody.has("name")
                            ? requestBody.get("name").getAsString()
                            : "World");

            // TODO: Add your business logic here
            JsonObject result = process(name);

            BufferedWriter writer = response.getWriter();
            writer.write(gson.toJson(result));

        } catch (Exception e) {
            logger.severe("[${{ values.functionName }}] Error: " + e.getMessage());
            response.setStatusCode(500);

            JsonObject error = new JsonObject();
            error.addProperty("status", "error");
            error.addProperty("message", "Internal server error");
            response.getWriter().write(gson.toJson(error));
        }
    }

    /**
     * Core business logic — replace or extend this method.
     */
    private JsonObject process(String name) {
        JsonObject result = new JsonObject();
        result.addProperty("function", "${{ values.functionName }}");
        result.addProperty("message", "Hello, " + name + "!");
        result.addProperty("status", "success");
        return result;
    }
}
