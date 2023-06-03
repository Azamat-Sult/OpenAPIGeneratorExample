package ru.example.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.example.client.model.RqParams;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RestService {

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public <Request, Response> Response send(RqParams<Request, Response> rqParams) {
        log.info(rqParams.toString());
        Mono<Response> webClientResponse;
        ResponseEntity<Response> restTemplateResponse;
        Response responseBody = null;
        if (rqParams.isSendByWebClient()) {
            webClientResponse = getWebClientResponse(rqParams);
            responseBody = webClientResponse.block();
        } else {
            restTemplateResponse = getRestTemplateResponse(rqParams);
            responseBody = restTemplateResponse.getBody();
        }
        return responseBody;
    }

    private <Response, Request> Mono<Response> getWebClientResponse(RqParams<Request, Response> rqParams) {
        if (rqParams.getResponseClass() != null) {
            return webClient
                    .method(rqParams.getMethod())
                    .uri(rqParams.getUrl())
                    .accept(rqParams.getRqMediaType())
                    .headers(header -> header.addAll(getHeaders(rqParams)))
                    .bodyValue(rqParams.getRequest())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, error -> {
                        if (HttpStatus.BAD_REQUEST == error.statusCode() || HttpStatus.UNPROCESSABLE_ENTITY == error.statusCode()) {
                            return Mono.empty();
                        } else {
                            return Mono.error(new ApplicationContextException(error.toString()));
                        }
                    })
                    .bodyToMono(rqParams.getResponseClass());
        } else if (rqParams.getParameterizedTypeReference() != null) {
            return webClient
                    .method(rqParams.getMethod())
                    .uri(rqParams.getUrl())
                    .accept(rqParams.getRqMediaType())
                    .headers(header -> header.addAll(getHeaders(rqParams)))
                    .bodyValue(rqParams.getRequest())
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, error -> {
                        if (HttpStatus.BAD_REQUEST == error.statusCode() || HttpStatus.UNPROCESSABLE_ENTITY == error.statusCode()) {
                            return Mono.empty();
                        } else {
                            return Mono.error(new ApplicationContextException(error.toString()));
                        }
                    })
                    .bodyToMono(rqParams.getParameterizedTypeReference());
        } else {
            throw new IllegalArgumentException("В параметрах запроса не задан класс ответа или параметризированный класс ответа");
        }
    }

    private <Response, Request> ResponseEntity<Response> getRestTemplateResponse(RqParams<Request, Response> rqParams) {
        if (rqParams.getResponseClass() != null) {
            return restTemplate.exchange(
                    rqParams.getUrl(),
                    rqParams.getMethod(),
                    new HttpEntity<>(rqParams.getRequest(), getHeaders(rqParams)),
                    rqParams.getResponseClass()
            );
        } else if (rqParams.getParameterizedTypeReference() != null) {
            return restTemplate.exchange(
                    rqParams.getUrl(),
                    rqParams.getMethod(),
                    new HttpEntity<>(rqParams.getRequest(), getHeaders(rqParams)),
                    rqParams.getParameterizedTypeReference()
            );
        } else {
            throw new IllegalArgumentException("В параметрах запроса не задан класс ответа или параметризированный класс ответа");
        }
    }

    private <Response, Request> MultiValueMap<String, String> getHeaders(RqParams<Request, Response> rqParams) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (!rqParams.getHeaders().isEmpty()) {
            rqParams.getHeaders().forEach(httpHeaders::add);
        }
        httpHeaders.setContentType(rqParams.getRqMediaType());
        httpHeaders.setAccept(List.of(rqParams.getRsMediaType()));
        return httpHeaders;
    }

}