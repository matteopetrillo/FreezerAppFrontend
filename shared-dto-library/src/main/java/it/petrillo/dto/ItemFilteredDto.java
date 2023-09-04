package it.petrillo.dto;

import lombok.Data;

@Data
public class ItemFilteredDto {
    private long id;
    private String name;
    private String category;
    private int availableQuantity;
}
