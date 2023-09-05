package it.petrillo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemFilteredDto {
    private Long id;
    private String name;
    private String category;
    private Integer availableQuantity;
}
