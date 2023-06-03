package ru.example.client.model;

import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Map;

@Data
public class RqParams<Request, Response> {

    private String url;
    private boolean sendByWebClient;
    private Request request;
    private Class<Response> responseClass;
    private ParameterizedTypeReference<Response> parameterizedTypeReference;
    private HttpMethod method = HttpMethod.POST;
    private MediaType rqMediaType = MediaType.APPLICATION_JSON;
    private MediaType rsMediaType = MediaType.APPLICATION_JSON;
    private Map<String, String> headers = Map.of();

}