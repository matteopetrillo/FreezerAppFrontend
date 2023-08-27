package it.petrillo.inventory.services;

import it.petrillo.dto.article.ArticleDTO;
import it.petrillo.inventory.persistence.ArticleEntity;
import it.petrillo.inventory.persistence.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleMapper articleMapper;


    public ArticleDTO getArticleById(int articleId) {
        Optional<ArticleEntity> articleOptional = articleRepository.findById(articleId);
        return articleOptional.map(articleEntity -> articleMapper.toDto(articleEntity)).orElse(null);
    }

    public List<ArticleDTO> getAllArticles() {
        return articleMapper.toDtoList(articleRepository.findAll());
    }

    public void insertArticle(ArticleDTO body) {
        ArticleEntity entity = articleMapper.toEntity(body);
        articleRepository.save(entity);
    }

}
