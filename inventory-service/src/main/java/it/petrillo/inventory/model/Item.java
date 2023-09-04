package it.petrillo.inventory.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_category", nullable = false)
    private ItemCategory itemCategory;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    private ItemInventory itemInventory;

}