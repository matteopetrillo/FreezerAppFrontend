package it.petrillo.inventory.controller;

import it.petrillo.dto.ItemDto;
import it.petrillo.dto.ItemRentedDto;
import it.petrillo.inventory.model.ItemAvailableDto;
import it.petrillo.inventory.services.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/item")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/all")
    public List<ItemDto> getItems() {
        log.info("Requested all items");
        return itemService.getItems();
    }

    @GetMapping("/filtered")
    public List<ItemAvailableDto> getDateFilteredItems(
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("Request all items rented in range from "+start+" to "+end);
        return itemService.getDateFilteredItems(start,end);
    }

    @GetMapping("/rented")
    public List<ItemRentedDto> getAllRentedItems() {
        log.info("Request all rented items.");
        return itemService.getAllRentedItems();
    }

    @PostMapping("/new-rent")
    public ResponseEntity<Long> rentItem(@RequestBody ItemRentedDto dto) {
        log.info("Placed new rent! " +
                "\n"+dto.getItemName()+" x"+dto.getQuantity()+" from "+dto.getStartingDate()+" to "+dto.getEndingDate()
        + " for production id "+dto.getProductionId());
        return itemService.rentItem(dto);
    }

    @GetMapping("/rented/by-prod")
    public List<ItemRentedDto> getRentsByProdId(@RequestParam("prodId") String id) {
        log.info("Request all items rented for production n."+id);
        return itemService.getRentsByProdId(id);
    }
}
