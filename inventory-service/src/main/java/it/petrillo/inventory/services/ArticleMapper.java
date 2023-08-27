package it.petrillo.inventory.services;

import it.petrillo.dto.article.ArticleDTO;
import it.petrillo.inventory.persistence.ArticleEntity;
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

    public ArticleDTO toDto(ArticleEntity articleEntity) {
        return Objects.isNull(articleEntity) ? null : modelMapper.map(articleEntity, ArticleDTO.class);
    }

    public ArticleEntity toEntity(ArticleDTO articleDTO) {
        return Objects.isNull(articleDTO) ? null : modelMapper.map(articleDTO, ArticleEntity.class);
    }

    public List<ArticleDTO> toDtoList(List<ArticleEntity> articleEntityList) {
        return articleEntityList.stream()
                .map(articleEntity -> modelMapper.map(articleEntity, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    public List<ArticleEntity> toEntityList(List<ArticleDTO> articleDtoList) {
        return articleDtoList.stream()
                .map(articleDto -> modelMapper.map(articleDto, ArticleEntity.class))
                .collect(Collectors.toList());
    }
}
