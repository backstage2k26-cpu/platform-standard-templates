package com.example;

import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class CloudFunctionTest {

    @Mock
    private HttpRequest request;

    @Mock
    private HttpResponse response;

    private StringWriter responseOut;
    private CloudFunction function;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        responseOut = new StringWriter();
        BufferedWriter writer = new BufferedWriter(responseOut);

        when(response.getWriter()).thenReturn(writer);
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("{}")));
        when(request.getMethod()).thenReturn("GET");

        function = new CloudFunction();
    }

    @Test
    public void testHelloWorld() throws IOException {
        when(request.getFirstQueryParameter("name")).thenReturn(Optional.empty());

        function.service(request, response);

        String output = responseOut.toString();
        assertTrue("Response should contain 'Hello'", output.contains("Hello"));
        assertTrue("Response should have success status", output.contains("success"));
    }

    @Test
    public void testWithNameParam() throws IOException {
        when(request.getFirstQueryParameter("name")).thenReturn(Optional.of("Backstage"));

        function.service(request, response);

        String output = responseOut.toString();
        assertTrue("Response should greet by name", output.contains("Backstage"));
    }
}
