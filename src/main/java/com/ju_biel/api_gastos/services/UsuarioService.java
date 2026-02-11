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
        Optional<Usuario> usuario= repository.findById(id);
        return usuario.get();
    }

    public Usuario insert (Usuario usuario) {
        //falta reconhecer senha ainda!
        return repository.save(usuario);
    }

    public void delete (Long id){
        repository.deleteById(id);
    }

    public Usuario update (Long id, Usuario usuario){
        Usuario entity = repository.getReferenceById(id);
        updateData(entity,usuario);
        return repository.save(entity);
    }

    private void updateData(Usuario entity, Usuario usuario) {
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());

    }
}
