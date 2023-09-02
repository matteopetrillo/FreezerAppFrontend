package it.petrillo.inventory.model;
import jakarta.persistence.*;

/**
 * Represents an article in the inventory.
 * An `Article` object stores information about a specific inventory item, including its unique
 * identifier, name, quantity, available quantity, and category. This class is mapped to a database
 * table for storing inventory data.
 */
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
    private int availableQuantity;
    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    /**
     * Constructs a new `Article` object with the specified attributes.
     *
     * @param id              The unique identifier of the article.
     * @param name            The name of the article.
     * @param quantity        The total quantity of the article in the inventory.
     * @param category        The category to which the article belongs.
     */
    public Article(Integer id, String name, int quantity, ArticleCategory category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.availableQuantity = quantity;
        this.category = category;
    }

    /**
     * Default constructor for creating an empty `Article` object.
     */
    public Article() {
    }

    // Getters and Setters
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

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
