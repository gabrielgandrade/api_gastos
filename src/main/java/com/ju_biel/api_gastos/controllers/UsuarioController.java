package com.ju_biel.api_gastos.controllers;


import com.ju_biel.api_gastos.entities.Usuario;
import com.ju_biel.api_gastos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value= "/{id}")
    public ResponseEntity<Usuario> findById( @PathVariable Long id) {
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = service.criarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }
}
