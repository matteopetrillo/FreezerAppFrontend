package it.petrillo.daily.service;

import it.petrillo.daily.model.DailyRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DailyRentService {

    @Autowired
    private final DailyRentRepository dailyRentRepository;

    public DailyRentService(DailyRentRepository dailyRentRepository) {
        this.dailyRentRepository = dailyRentRepository;
    }

    public List<DailyRent> getAllDailyRents() {
        return dailyRentRepository.findAll();
    }

    public void insertDay(DailyRent dailyRent) {
        dailyRentRepository.insert(dailyRent);
    }

    public DailyRent getDayByDate(int year, int month, int day) {
        Optional<DailyRent> dailyRentOptional = dailyRentRepository.getRentsByDate(year,month,day);
        return dailyRentOptional.orElse(null);
    }

    public Map<Integer,Integer> getRentsByDate(int year, int month, int day) {
        Optional<DailyRent> dailyRentOptional = dailyRentRepository.getRentsByDate(year,month,day);
        if (dailyRentOptional.isPresent()) {
            DailyRent dailyRent = dailyRentOptional.get();
            return dailyRent.getRents();
        }
        return null;
    }

    public void addArticlesToRent(int year, int month, int day, Map<Integer, Integer> rentArticles) {
        DailyRent dailyRent = getDayByDate(year,month,day);
        dailyRent.mergeMaps(rentArticles);
        dailyRentRepository.save(dailyRent);
    }

    public void removeById(String id) {
        dailyRentRepository.deleteById(id);
    }
    public void insertDaysForYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DailyRent day = new DailyRent(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
            dailyRentRepository.save(day);
        }
    }
}
