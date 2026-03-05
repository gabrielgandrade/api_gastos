package com.ju_biel.api_gastos.controllers;

import com.ju_biel.api_gastos.dto.AuthDTO;
import com.ju_biel.api_gastos.dto.AuthResponseDTO;
import com.ju_biel.api_gastos.dto.SenhaDTO;
import com.ju_biel.api_gastos.entities.Usuario;
import com.ju_biel.api_gastos.repositories.UsuarioRepository;
import com.ju_biel.api_gastos.services.TokenService;
import com.ju_biel.api_gastos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
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

    @GetMapping("/me")
    public ResponseEntity<Usuario> getUsuarioLogado (@AuthenticationPrincipal Usuario usuario ){
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Usuario>alterarSenha(@AuthenticationPrincipal Usuario usuario, @RequestBody SenhaDTO dto){
        var alterarSenhaUsuario= usuarioService.editarSenha(usuario.getId(), dto.senhaAtual(), dto.novaSenha());
        return ResponseEntity.ok(alterarSenhaUsuario);
    }
}

