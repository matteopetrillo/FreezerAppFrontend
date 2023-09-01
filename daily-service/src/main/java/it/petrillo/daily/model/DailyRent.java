package it.petrillo.daily.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Data
@Document
public class DailyRent {
    @Id
    private String id;
    private int day;
    private int month;
    private int year;
    private Map<Integer,Integer> rents = new HashMap<>();

    public DailyRent(int day, int month, int year, Map<Integer, Integer> rents) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.rents = rents;
    }

    public DailyRent(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public DailyRent() {
    }

    public void mergeMaps(Map<Integer,Integer> otherMap) {
        for (Map.Entry<Integer, Integer> entry : otherMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (rents.containsKey(key))
                rents.put(key, rents.get(key)+value);
            else
                rents.put(key,value);
        }
    }
}
