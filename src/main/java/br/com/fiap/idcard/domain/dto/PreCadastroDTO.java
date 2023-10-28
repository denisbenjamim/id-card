package br.com.fiap.idcard.domain.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.fiap.idcard.domain.entities.PreCadastroEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PreCadastroDTO(
     UUID codigoPreCadastro,
     @NotBlank String nomeCompleto,
     @Email String email,
     @NotNull LocalDate dataNascimento,
     @Size(min = 1) List<DocumentoDTO> documentos
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
