package it.petrillo.production.service;

import it.petrillo.dto.ItemRentedBasicDto;
import it.petrillo.dto.ItemRentedDto;
import it.petrillo.production.model.Production;
import it.petrillo.production.model.dao.ProductionDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductionService {

    private ProductionDAO productionDAO;
    private RestTemplate restTemplate;

    public List<Production> getAllProduction() {
        return productionDAO.findAll();
    }

    public String createProduction(Production body) {
        Production production = productionDAO.insert(body);
        String id = production.getId();
        for (ItemRentedBasicDto basicDto : production.getEquipment()) {
            ItemRentedDto rentedDto = new ItemRentedDto(
                    basicDto.getItemId(),
                    id, basicDto.getQuantity(),
                    production.getStartDate(),
                    production.getEndDate()
            );
            restTemplate.postForObject("http://localhost:8080/item/new-rent", rentedDto,Long.TYPE);
        }

        return production.getId();
    }

}
