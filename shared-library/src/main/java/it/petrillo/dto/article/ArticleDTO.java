package it.petrillo.dto.article;

public class ArticleDTO {

    private int id;
    private String name;
    private ArticleCategory category;
    private int quantity;

    public ArticleDTO(int id, String name, ArticleCategory category, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public ArticleDTO() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
