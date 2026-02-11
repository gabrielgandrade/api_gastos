package com.ju_biel.api_gastos.controllers;


import com.ju_biel.api_gastos.entities.Usuario;
import com.ju_biel.api_gastos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value= "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //Metodo pra recuperar/ retornar os dados de todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Metodo pra retornar um usuário específico, por id
    @GetMapping(value= "/{id}")
    public ResponseEntity<Usuario> findById( @PathVariable Long id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    //Metodo pra inserir novo usuário: Aqui, pega a url atual do request, adc o caminho e coloca o id real convertendo p URI (endereco que identifica)
    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario){
        Usuario novoUsuario = service.insert(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }

    //Metodo pra apagar
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Metodo p atualizar dados
    @PutMapping(value="/{id}")
    public ResponseEntity<Usuario>update(@PathVariable Long id, @RequestBody Usuario usuario ){
        usuario= service.update(id, usuario);
        return ResponseEntity.ok().body(usuario);
    }
}
