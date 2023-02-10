package com.tenorio.pokemontcg.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class set {

    private String id;
    private String name;
    private String series;
    private int printedTotal;
    private int total;
}
