package br.com.fiap.idcard.domain.dto;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fiap.idcard.domain.entities.DocumentoEntity;
import br.com.fiap.idcard.domain.entities.TipoDocumento;

public record DocumentoDTO(
    @JsonIgnore UUID codigoPreCadastro,
    String identificadorDocumento,
    LocalDate dataEmissao,
    byte[] dadosArquivo,
    String nomeArquivo,
    String contentType,
    MultipartFile arquivo,
    String tipo
) {
   
    public DocumentoDTO( UUID codigoPreCadastro, String identificadorDocumento, LocalDate dataEmissao, String tipo){
        this(codigoPreCadastro, identificadorDocumento, dataEmissao, null, null, null, null, tipo);
    }

    public DocumentoEntity toDocumentoEntity(){
        return toDocumentoEntity(tipo);
    }

    public DocumentoEntity toDocumentoEntity(String tipo){
        final DocumentoEntity entity = TipoDocumento.getInstanceBy(tipo);

        entity.setContentType(contentType);
        entity.setDadosArquivo(dadosArquivo);
        entity.setDataEmissao(dataEmissao);
        entity.setIdentificadorDocumento(identificadorDocumento);
        entity.setNomeArquivo(nomeArquivo);

        return entity;
    }

}
