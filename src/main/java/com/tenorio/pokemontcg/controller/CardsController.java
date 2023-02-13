package com.tenorio.pokemontcg.controller;

import com.tenorio.pokemontcg.domain.Card;
import com.tenorio.pokemontcg.service.CardsService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CardsController {

    private final CardsService cardsService;

    public CardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @GetMapping(value = "/cards")
    public List<Card> getCards(@RequestParam("query") String query) {
        return cardsService.getCardsWithQuery(query);
    }
}
