package br.com.fiap.idcard.domain.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.fiap.idcard.domain.entities.PreCadastroEntity;

public record PreCadastroDTO(
     UUID codigoPreCadastro,
     String nomeCompleto,
     String email,
     LocalDate dataNascimento,
     List<DocumentoDTO> documentos
) {

     public PreCadastroEntity toEntity() {
         return PreCadastroEntity.builder()
               .codigoPreCadastro(codigoPreCadastro)
               .nomeCompleto(nomeCompleto)
               .email(email)
               .dataNascimento(dataNascimento) 
               .documentos(PreCadastroEntity.toListDocumentoEntity(documentos))
          .build();
     }
    
}
