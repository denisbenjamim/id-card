package br.com.fiap.idcard.domain.controller;

import br.com.fiap.idcard.domain.dto.ArquivoDocumentoDTO;
import br.com.fiap.idcard.domain.dto.PreCadastroDTO;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import br.com.fiap.idcard.domain.services.PreCadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pre-cadastros")
public class PreCadastroController {
    @Autowired
    private PreCadastroService service;
    
    @GetMapping
    public ResponseEntity<List<PreCadastroDTO>> getPreCadastroComStatus(@Validated @RequestParam StatusPreCadastro status){
        return ResponseEntity.ok(service.getPreCadastroComStatus(status));
    }

    @PostMapping
    public ResponseEntity<UUID> criarPreCadastro(@RequestBody final PreCadastroDTO dto){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.salvar(dto));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(ArquivoDocumentoDTO documentosDTO){
       
        service.salvarArquivoDocumento(documentosDTO);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }
}
