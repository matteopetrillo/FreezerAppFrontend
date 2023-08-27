package it.petrillo.inventory.services;

import it.petrillo.dto.article.ArticleDTO;
import it.petrillo.inventory.persistence.Article;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ArticleDTO toDto(Article article) {
        return Objects.isNull(article) ? null : modelMapper.map(article, ArticleDTO.class);
    }

    public Article toEntity(ArticleDTO articleDTO) {
        return Objects.isNull(articleDTO) ? null : modelMapper.map(articleDTO, Article.class);
    }

    public List<ArticleDTO> toDtoList(List<Article> articleList) {
        return articleList.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    public List<Article> toEntityList(List<ArticleDTO> articleDtoList) {
        return articleDtoList.stream()
                .map(articleDto -> modelMapper.map(articleDto, Article.class))
                .collect(Collectors.toList());
    }
}
