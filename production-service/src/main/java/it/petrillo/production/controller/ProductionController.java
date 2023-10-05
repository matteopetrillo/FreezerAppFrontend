package it.petrillo.production.controller;

import it.petrillo.production.model.Production;
import it.petrillo.production.service.ProductionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/production")
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping("/all")
    public List<Production> getAllProductions() {
        log.info("Request list of all productions");
        return productionService.getAllProduction();
    }

    @PostMapping("/new")
    public String createProduction(@RequestBody Production body) {
        log.info("A new production in coming!"
        +"\n"+body.getName()+" from "+body.getStartDate()+" to "+body.getEndDate()+" for customer "+body.getCustomer()
        + ". Production will require following items: "+body.getEquipment());
        return productionService.createProduction(body);
    }


}
