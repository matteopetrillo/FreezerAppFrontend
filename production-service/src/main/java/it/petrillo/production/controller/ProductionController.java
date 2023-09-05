package it.petrillo.production.controller;

import it.petrillo.production.model.Production;
import it.petrillo.production.service.ProductionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/production")
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping("/all")
    public List<Production> getAllProductions() {
        return productionService.getAllProduction();
    }

    @PostMapping("/new")
    public String createProduction(@RequestBody Production body) {
        return productionService.createProduction(body);
    }


}
