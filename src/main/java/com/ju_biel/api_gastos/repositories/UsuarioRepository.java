package com.ju_biel.api_gastos.repositories;

import com.ju_biel.api_gastos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository <Usuario,Long>{

    UserDetails findByEmail(String email);

    String email(String email);
}
