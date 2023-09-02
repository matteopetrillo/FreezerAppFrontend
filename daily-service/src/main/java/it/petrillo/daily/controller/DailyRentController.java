package it.petrillo.daily.controller;

import it.petrillo.daily.model.DailyRent;
import it.petrillo.daily.service.DailyRentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/days")
public class DailyRentController {

    private final DailyRentService dailyRentService;

    @Autowired
    public DailyRentController(DailyRentService dailyRentService) {
        this.dailyRentService = dailyRentService;
    }

    @GetMapping()
    public List<DailyRent> getAllDailyRents() {
        log.info("Richiesto l'elenco di tutti i giorni");
        return dailyRentService.getAllDailyRents();
    }


    @GetMapping("/{year}-{month}-{day}")
    public DailyRent getDayByDate(@PathVariable("year") int year,
                                  @PathVariable("month")int month,
                                  @PathVariable("day") int day) {
        log.info("Richiesta la visione completa per il giorno: "+day+"/"+month+"/"+year);
        return dailyRentService.getDayByDate(year,month,day);
    }

    @GetMapping("/{year}-{month}-{day}/{year2}-{month2}-{day2}")
    public List<DailyRent> getDaysByDateRange(@PathVariable("year") int year, @PathVariable("month")int month,
                                         @PathVariable("day") int day, @PathVariable("year2") int year2,
                                         @PathVariable("month2") int month2, @PathVariable("day2") int day2 ) {
        log.info("Richiesta la visione dei giorni per il range da: " + day + "/" + month + "/" + year+" a: "
                + day2 + "/" + month2 + "/" + year2);
        return dailyRentService.getDaysByDateRange(year,month,day,year2,month2,day2);
    }


    @PutMapping("/add-rents/{year}-{month}-{day}")
    public void addRentsToDay(@PathVariable("year") int year,
                              @PathVariable("month")int month,
                              @PathVariable("day") int day,
                              @RequestBody Map<Integer,Integer> rentArticles) {
        log.info("Aggiunta la lista: "+rentArticles.toString()+" al giorno "+day+"/"+month+"/"+year);
        dailyRentService.addArticlesToRent(year,month,day,rentArticles);
    }

    @DeleteMapping("/{id}")
    public void removeDayById(@PathVariable("id") String id) {
        log.info("Eliminato il record con id: "+id);
        dailyRentService.removeById(id);
    }

    @GetMapping("/insertdays-{year}")
    public String addRecordForYear(@PathVariable("year") int year) {
        dailyRentService.insertDaysForYear(year);
        log.info("Aggiunti i record per l'anno "+year);
        return "Aggiunti i record per l'anno "+year;
    }


}
