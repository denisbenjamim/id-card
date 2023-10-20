package br.com.fiap.idcard.domain.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.idcard.domain.controller.dto.ArquivoDocumentoDTO;
import br.com.fiap.idcard.domain.entities.PreCadastroEntity;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import br.com.fiap.idcard.domain.services.PreCadastroService;

@RestController
@RequestMapping("/pre-cadastros")
public class PreCadastroController {
    @Autowired
    private PreCadastroService service;
    
    @GetMapping
    public ResponseEntity<List<PreCadastroEntity>> getPreCadastroComStatus(@Validated @RequestParam StatusPreCadastro status){
        return ResponseEntity.ok(service.getPreCadastroComStatus(status));
    }

    @PostMapping
    public ResponseEntity<UUID> criarPreCadastro(@RequestBody final PreCadastroEntity entity){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.salvar(entity).getCodigoPreCadastro());
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(ArquivoDocumentoDTO documentosDTO){
       
        service.salvarArquivoDocumento(documentosDTO);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
}
