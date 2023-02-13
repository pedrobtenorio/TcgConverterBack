package com.tenorio.pokemontcg.domain;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Card {

    private String id;
    private String name;
    private String supertype;
    private String level;
    private String number;
    private String artist;
    private String rarity;
    private String flavorText;
    private set set;
    private images images;

}
