package br.com.fiap.idcard.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreCadastroController {
    
    @GetMapping("/")
    public String get(){
        return "FIAP";
    }
}
