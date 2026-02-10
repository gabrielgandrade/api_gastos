package com.ju_biel.api_gastos.repositories;

import com.ju_biel.api_gastos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario,Long>{
}
