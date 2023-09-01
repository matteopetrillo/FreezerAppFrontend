package it.petrillo.daily.service;

import it.petrillo.daily.model.DailyRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<Integer,Integer> getRentsByDate(int year, int month, int day) {
        Optional<DailyRent> dailyRentOptional = dailyRentRepository.getRentsByDate(year,month,day);
        if (dailyRentOptional.isPresent()) {
            DailyRent dailyRent = dailyRentOptional.get();
            return dailyRent.getRents();
        }
        return null;
    }
}
