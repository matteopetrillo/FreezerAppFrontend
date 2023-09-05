package it.petrillo.inventory.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "item_rented", indexes = @Index(columnList = "starting_date"))
public class ItemRented {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate;

    @Column(name = "ending_date", nullable = false)
    private LocalDate endingDate;

    @Column(name = "production_id")
    private String production_id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public ItemRented(Item item, LocalDate startingDate, LocalDate endingDate, String production_id, Integer quantity) {
        this.item = item;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.production_id = production_id;
        this.quantity = quantity;
    }
}