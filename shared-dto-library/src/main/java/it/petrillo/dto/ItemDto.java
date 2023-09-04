package it.petrillo.dto;

import lombok.Data;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private String category;
    private Integer totalQuantity;

}
