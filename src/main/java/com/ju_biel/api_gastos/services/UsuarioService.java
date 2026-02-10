package com.ju_biel.api_gastos.services;

import com.ju_biel.api_gastos.entities.Usuario;
import com.ju_biel.api_gastos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario findById(Long id){
        Optional<Usuario> obj= repository.findById(id);
        return obj.get();
    }

    public Usuario criarUsuario(Usuario usuario) {
        //falta reconhecer senha ainda!
        return repository.save(usuario);
    }
}
