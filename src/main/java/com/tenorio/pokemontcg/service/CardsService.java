package com.tenorio.pokemontcg.service;

import com.tenorio.pokemontcg.domain.Card;
import com.tenorio.pokemontcg.domain.CardResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CardsService {
    private final RestTemplate restTemplate;
    private final ExcelGenerator excelGenerator;
    @Value("${apiKey}")
    private String apiKey;


    public CardsService(RestTemplateBuilder restTemplateBuilder, ExcelGenerator excelGenerator) {
        this.restTemplate = restTemplateBuilder.build();
        this.excelGenerator = excelGenerator;
    }

    public ResponseEntity<InputStreamResource> getCardsWithQuery(String query) {
        String url = "https://api.pokemontcg.io/v2/cards?q=name:" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<CardResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, CardResponse.class);
        CardResponse cardResponseWrapper = response.getBody();
        if (cardResponseWrapper == null) {
            throw new IllegalStateException("Could not retrieve cards from API. Response body was null.");
        }
        List<Card> cards = cardResponseWrapper.getData();
        if(query.contains("*")) {
            query = query.replace("*", "");
        }
        String name = String.format("%s.xlsx", query);
        return this.excelGenerator.exportToExcel(cards, name);
    }



}
