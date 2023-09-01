package it.petrillo.inventory.services;

import it.petrillo.inventory.model.ArticleDTO;
import it.petrillo.inventory.persistence.Article;
import it.petrillo.inventory.persistence.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public ArticleDTO getArticleById(int articleId) throws IllegalArgumentException {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isPresent())
            return articleOptional.map(articleMapper::toDto).orElse(null);
        else
            throw new IllegalArgumentException("Oggetto non presente");
    }

    public List<ArticleDTO> getAllArticles() {
        return articleMapper.toDtoList(articleRepository.findAll());
    }

    public void insertArticle(ArticleDTO body) {
        Article entity = articleMapper.toEntity(body);
        articleRepository.save(entity);
    }

}
