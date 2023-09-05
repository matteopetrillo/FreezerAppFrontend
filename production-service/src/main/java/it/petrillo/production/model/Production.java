package it.petrillo.production.model;


import it.petrillo.dto.ItemRentedBasicDto;
import it.petrillo.dto.ItemRentedDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@Document(collection = "productions")
public class Production {
    @Id
    private String id;
    private String name;
    private String customer;
    private Double budget;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ItemRentedBasicDto> equipment;

    public Production(String name, String customer, Double budget, LocalDate startDate, LocalDate endDate, List<ItemRentedBasicDto> equipment) {
        this.name = name;
        this.customer = customer;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.equipment = equipment;
    }
}

