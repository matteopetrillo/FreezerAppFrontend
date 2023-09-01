package it.petrillo.daily.controller;

import it.petrillo.daily.model.DailyRent;
import it.petrillo.daily.service.DailyRentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping(path = "api/v1/days")
public class DailyRentController {

    @Autowired
    private final DailyRentService dailyRentService;

    public DailyRentController(DailyRentService dailyRentService) {
        this.dailyRentService = dailyRentService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.FOUND)
    public List<DailyRent> getAllDailyRents() {
        log.info("Richiesto l'elenco di tutti i giorni");
        return dailyRentService.getAllDailyRents();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void insertDay(@RequestBody DailyRent dailyRent) {
        log.info("Inserito il giorno: {}", dailyRent);
        dailyRentService.insertDay(dailyRent);
    }

    @GetMapping("/list/{year}-{month}-{day}")
    @ResponseStatus(HttpStatus.FOUND)
    public Map<Integer,Integer> getRentsByDate(@PathVariable("year") int year,
                                             @PathVariable("month")int month,
                                             @PathVariable("day") int day ) {
        log.info("Richiesta la lista delle prenotazioni per il giorno: "+day+"/"+month+"/"+year);
        return dailyRentService.getRentsByDate(year,month,day);
    }

    @GetMapping("/{year}-{month}-{day}")
    @ResponseStatus(HttpStatus.FOUND)
    public DailyRent getDayByDate(@PathVariable("year") int year,
                                  @PathVariable("month")int month,
                                  @PathVariable("day") int day) {
        log.info("Richiesta la visione completa per il giorno: "+day+"/"+month+"/"+year);
        return dailyRentService.getDayByDate(year,month,day);
    }

    @PutMapping("/add-rents/{year}-{month}-{day}")
    @ResponseStatus(HttpStatus.OK)
    public void addRentsToDay(@PathVariable("year") int year,
                              @PathVariable("month")int month,
                              @PathVariable("day") int day,
                              @RequestBody Map<Integer,Integer> rentArticles) {
        log.info("Aggiunta la lista: "+rentArticles.toString()+" al giorno "+day+"/"+month+"/"+year);
        dailyRentService.addArticlesToRent(year,month,day,rentArticles);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
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
