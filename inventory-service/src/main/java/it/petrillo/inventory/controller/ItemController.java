package it.petrillo.inventory.controller;

import it.petrillo.dto.ItemDto;
import it.petrillo.dto.ItemFilteredDto;
import it.petrillo.dto.ItemRentedDto;
import it.petrillo.inventory.model.Item;
import it.petrillo.inventory.model.ItemRented;
import it.petrillo.inventory.services.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/item")
public class ItemController {

    private ItemService itemService;

    @GetMapping("/all")
    public List<ItemDto> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/filtered")
    public List<ItemFilteredDto> getDateFilteredItems(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        return itemService.getDateFilteredItems(start,end);
    }

    @GetMapping("/rented")
    public List<ItemRentedDto> getAllRentedItems() {
        return itemService.getAllRentedItems();
    }


}
