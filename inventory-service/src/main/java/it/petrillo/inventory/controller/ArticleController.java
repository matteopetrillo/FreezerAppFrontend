package it.petrillo.inventory.controller;


import it.petrillo.inventory.model.ArticleDTO;
import it.petrillo.inventory.services.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        log.info("Richiesta la lista di tutti gli articoli");
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleDTO getArticleById(@PathVariable Integer id) {
        log.info("Richiesto l'articolo: {}", id);
        return articleService.getArticleById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void insertArticle(@RequestBody ArticleDTO article) {
        log.info("Nuovo Articolo Inserito: {}",article);
        articleService.insertArticle(article);
    }
}
