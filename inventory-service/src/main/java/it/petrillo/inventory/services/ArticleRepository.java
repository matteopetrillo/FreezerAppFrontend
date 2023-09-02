package it.petrillo.inventory.services;

import it.petrillo.inventory.model.Article;
import it.petrillo.inventory.model.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing article data in the database.
 * The `ArticleRepository` interface extends the Spring Data JPA `JpaRepository` and provides
 * methods for querying and managing article data in the database. It includes methods for
 * searching articles by name containing a specific term and by category.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    /**
     * Retrieves a list of articles whose names contain the specified search term.
     *
     * @param searchTerm The search term used to filter articles by name.
     * @return A list of {@link Article} objects matching the search criteria.
     */
    List<Article> findArticleByNameContaining(String searchTerm);

    /**
     * Retrieves a list of articles belonging to the specified category.
     *
     * @param category The category of articles to retrieve.
     * @return A list of {@link Article} objects belonging to the specified category.
     */
    List<Article> findArticleByCategory(ArticleCategory category);
}
