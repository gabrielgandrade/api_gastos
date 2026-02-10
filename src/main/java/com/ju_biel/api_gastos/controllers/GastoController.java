package com.ju_biel.api_gastos.controllers;

import com.ju_biel.api_gastos.repositories.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ju_biel.api_gastos.entities.Gasto;

import java.util.List;


@RestController
@RequestMapping(value= "/gastos")

public class GastoController {

    @Autowired
    private GastoRepository repository;

    @GetMapping
    public List<Gasto> findAll(){

        return repository.findAll();
    }

    @PostMapping
    public Gasto salvarGasto (@RequestBody Gasto novoGasto){
        return  repository.save(novoGasto);
    }

}
