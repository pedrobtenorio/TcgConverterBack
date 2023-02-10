package com.tenorio.pokemontcg.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CardResponse {
    private List<Card> data;
    private int page;
    private int pageSize;
    private int count;
    private int totalCount;

}
