package it.petrillo.inventory.controller;

import it.petrillo.inventory.model.Article;
import it.petrillo.inventory.services.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling HTTP requests related to articles in the inventory.
 *
 * The `ArticleController` defines RESTful endpoints for retrieving information about articles,
 * including their details, available quantities, and filtering by name or category. It also
 * provides the ability to insert new articles into the inventory.
 */
@Slf4j
@RestController
@RequestMapping(path = "api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Retrieves a list of all articles in the inventory.
     *
     * @return A list of {@link Article} objects representing all articles.
     */
    @GetMapping("/all")
    public List<Article> getAllArticles() {
        log.info("Richiesta la lista di tutti gli articoli");
        return articleService.getAllArticles();
    }

    /**
     * Retrieves a list of articles with names containing the specified search term.
     *
     * @param searchTerm The search term used to filter articles by name.
     * @return A list of {@link Article} objects matching the search criteria.
     */
    @GetMapping("/by-name")
    public List<Article> getArticleByNameMatch(@RequestParam("name") String searchTerm) {
        log.info("Richiesti gli articoli che contengono "+searchTerm+" nel nome");
        return articleService.getArticleByNameMatch(searchTerm);
    }

    /**
     * Retrieves a list of articles by their category.
     *
     * @param articleCategory The category of articles to retrieve.
     * @return A list of {@link Article} objects belonging to the specified category.
     */
    @GetMapping("/by-category")
    public List<Article> getArticleByCategoryMatch(@RequestParam("category") String articleCategory) {
        log.info("Richiesti gli articoli appartenenti alla categoria: "+articleCategory);
        return articleService.getArticleByCategory(articleCategory);
    }

    /**
     * Retrieves a list of articles with available quantities based on a date range.
     *
     * @param year   The start year of the date range.
     * @param month  The start month of the date range.
     * @param day    The start day of the date range.
     * @param year2  The end year of the date range.
     * @param month2 The end month of the date range.
     * @param day2   The end day of the date range.
     * @return A list of {@link Article} objects with updated available quantities.
     */
    @GetMapping("/{year}-{month}-{day}/{year2}-{month2}-{day2}")
    public List<Article> getArticleAvailableByDateRange(@PathVariable("year") int year, @PathVariable("month")int month,
                                              @PathVariable("day") int day, @PathVariable("year2") int year2,
                                              @PathVariable("month2") int month2, @PathVariable("day2") int day2 ) {
        log.info("Richiesta la visione dei giorni per il range da: " + day + "/" + month + "/" + year+" a: "
                + day2 + "/" + month2 + "/" + year2);
        return articleService.getArticleAvailableByDateRange(year,month,day,year2,month2,day2);
    }

    /**
     * Retrieves the details of an article by its unique identifier.
     *
     * @param id The unique identifier of the article.
     * @return An {@link Article} object representing the article details.
     */
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Integer id) {
        log.info("Richiesto l'articolo: {}", id);
        return articleService.getArticleById(id);
    }

    /**
     * Inserts a new article into the inventory.
     *
     * @param article The {@link Article} object representing the article to be inserted.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insertArticle(@RequestBody Article article) {
        log.info("Nuovo Articolo Inserito: {}",article);
        articleService.insertArticle(article);
    }
}
