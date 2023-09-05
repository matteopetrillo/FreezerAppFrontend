package it.petrillo.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemRentedDto {
    private Long itemId;
    private String productionId;
    private String itemName;
    private Integer quantity;
    private LocalDate startingDate;
    private LocalDate endingDate;

    public ItemRentedDto(Long itemId, String productionId, Integer quantity, LocalDate startingDate, LocalDate endingDate) {
        this.itemId = itemId;
        this.productionId = productionId;
        this.quantity = quantity;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }
}
