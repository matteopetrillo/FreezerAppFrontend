package it.petrillo.daily;

import it.petrillo.daily.model.DailyRent;
import it.petrillo.daily.service.DailyRentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DailyRentApplication {
    public static void main(String[] args) {
        SpringApplication.run(DailyRentApplication.class,args);}

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}
