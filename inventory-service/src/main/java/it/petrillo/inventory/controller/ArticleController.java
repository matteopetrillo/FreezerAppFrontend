package it.petrillo.inventory.controller;

import it.petrillo.dto.article.ArticleDTO;
import it.petrillo.inventory.ArticleApplication;
import it.petrillo.inventory.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleDTO getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void insertArticle(@RequestBody ArticleDTO article) {
        articleService.insertArticle(article);
    }
}
