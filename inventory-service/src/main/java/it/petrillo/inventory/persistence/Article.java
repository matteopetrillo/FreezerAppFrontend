package it.petrillo.inventory.persistence;

import it.petrillo.inventory.model.ArticleCategory;
import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @SequenceGenerator(
            name = "article_id_sequence",
            sequenceName = "article_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_id_sequence"
    )
    private Integer id;
    private String name;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    public Article(Integer id, String name, int quantity, ArticleCategory category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public Article() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

}
