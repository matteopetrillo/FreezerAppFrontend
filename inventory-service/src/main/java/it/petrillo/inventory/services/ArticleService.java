package it.petrillo.inventory.services;

import it.petrillo.daily.model.DailyRent;
import it.petrillo.inventory.model.Article;
import it.petrillo.inventory.model.ArticleCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class that handles operations related to articles in the inventory.
 */
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, RestTemplate restTemplate) {
        this.articleRepository = articleRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves an article by its unique identifier.
     *
     * @param articleId The unique identifier of the article.
     * @return An {@link Article} object representing the article, or null if not found.
     * @throws IllegalArgumentException If the provided articleId is invalid.
     */
    public Article getArticleById(int articleId) throws IllegalArgumentException {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        return articleOptional.orElse(null);
    }

    /**
     * Retrieves a list of all articles in the inventory.
     *
     * @return A list of {@link Article} objects representing all articles.
     */
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    /**
     * Retrieves a list of articles with names containing the specified search term.
     *
     * @param searchTerm The search term used to filter articles by name.
     * @return A list of {@link Article} objects matching the search criteria.
     */
    public List<Article> getArticleByNameMatch(String searchTerm) {
        return articleRepository.findArticleByNameContaining(searchTerm);
    }

    /**
     * Inserts a new article into the inventory.
     *
     * @param body The {@link Article} object representing the article to be inserted.
     */
    public void insertArticle(Article body) {
        articleRepository.save(body);
    }

    /**
     * Retrieves a list of articles by their category.
     *
     * @param category The category of articles to retrieve.
     * @return A list of {@link Article} objects belonging to the specified category.
     */
    public List<Article> getArticleByCategory(String category) {
        return articleRepository.findArticleByCategory(ArticleCategory.valueOf(category));
    }

    /**
     * This method retrieves a list of articles from the inventory database and updates their
     * available quantities based on rental data within the specified date range. It uses a REST
     * API to fetch daily rental records for the given date range and adjusts the available
     * quantities of articles accordingly.
     *
     * @param year   The start year of the date range.
     * @param month  The start month of the date range.
     * @param day    The start day of the date range.
     * @param year2  The end year of the date range.
     * @param month2 The end month of the date range.
     * @param day2   The end day of the date range.
     * @return A list of {@link Article} objects with updated available quantities.
     */
    public List<Article> getArticleAvailableByDateRange(int year, int month, int day, int year2, int month2, int day2) {
        String url = "http://localhost:8081/api/v1/days/"+year+"-"+month+"-"+day+"/"+year2+"-"+month2+"-"+day2;

        List<DailyRent> dailyRentList = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DailyRent>>() {}).getBody();

        List<Article> articles = getAllArticles();

        if (!dailyRentList.isEmpty()) {
            List<Map<Integer,Integer>> rentedQuantityList = dailyRentList.stream()
                    .map(DailyRent::getRents)
                    .toList();

            Map<Integer, Integer> mergedMap = new HashMap<>();

            for (Map<Integer, Integer> map : rentedQuantityList) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    int key = entry.getKey();
                    int value = entry.getValue();
                    mergedMap.merge(key, value, Integer::sum);
                }
            }

            for (Article article : articles) {
                Integer id = article.getId();
                if (mergedMap.containsKey(id))
                    article.setAvailableQuantity(article.getQuantity()-mergedMap.get(id));
                else
                    article.setAvailableQuantity(article.getQuantity());
            }

        }
        else {
            articles.forEach(a -> a.setAvailableQuantity(a.getQuantity()));
        }

        return articles;
    }
}
