package com.ju_biel.api_gastos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ju_biel.api_gastos.entities.Gasto;


public interface GastoRepository extends JpaRepository<Gasto, Long> {


}
