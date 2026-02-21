package com.ju_biel.api_gastos.controllers;

import com.ju_biel.api_gastos.dto.AuthDTO;
import com.ju_biel.api_gastos.dto.AuthResponseDTO;
import com.ju_biel.api_gastos.entities.Usuario;
import com.ju_biel.api_gastos.repositories.UsuarioRepository;
import com.ju_biel.api_gastos.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody AuthDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth =this.authenticationManager.authenticate(usernamePassword);

        var token= tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDTO(token));


    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody Usuario usuario){
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaCriptografada=new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
        }
    }

