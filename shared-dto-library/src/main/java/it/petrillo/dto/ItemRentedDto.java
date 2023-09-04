package it.petrillo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ItemRentedDto {
    private Long itemId;
    private String itemName;
    private Integer quantity;
    private LocalDate startingDate;
    private LocalDate endingDate;
}
