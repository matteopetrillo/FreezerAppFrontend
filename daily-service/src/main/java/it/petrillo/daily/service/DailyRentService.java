package it.petrillo.daily.service;

import it.petrillo.daily.model.DailyRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public DailyRent getDayByDate(int year, int month, int day) {
        Optional<DailyRent> dailyRentOptional = dailyRentRepository.getRentsByDate(year,month,day);
        return dailyRentOptional.orElse(null);
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

    public List<DailyRent> getDaysByDateRange(int year, int month, int day, int year2, int month2, int day2) {
        List<DailyRent> dailyRents = new ArrayList<>();

        Date startDate = createNewDate(year, month, day);
        Date endDate = createNewDate(year2, month2, day2);

        Calendar rentCalendar = Calendar.getInstance();
        rentCalendar.setTime(startDate);

        while (!rentCalendar.getTime().after(endDate)) {
            int tmpYear = rentCalendar.get(Calendar.YEAR);
            int tmpMonth = rentCalendar.get(Calendar.MONTH) + 1;
            int tmpDay = rentCalendar.get(Calendar.DAY_OF_MONTH);

            dailyRents.add(getDayByDate(tmpYear,tmpMonth,tmpDay));

            rentCalendar.add(Calendar.DATE, 1);
        }

        return dailyRents;
    }

    private Date createNewDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }
}
