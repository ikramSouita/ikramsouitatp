package com.example.tpjawrs;

import com.example.tpjawrs.enums.TypeCompte;
import com.example.tpjawrs.model.Compte;
import com.example.tpjawrs.repository.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;



@SpringBootApplication
public class TpJawrsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TpJawrsApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));
            compteRepository.findAll().forEach(compte -> {
                System.out.println(compte.toString());
            });


        };
    }
}
