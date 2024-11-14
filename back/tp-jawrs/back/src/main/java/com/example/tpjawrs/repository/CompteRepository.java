package com.example.tpjawrs.repository;

import com.example.tpjawrs.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
