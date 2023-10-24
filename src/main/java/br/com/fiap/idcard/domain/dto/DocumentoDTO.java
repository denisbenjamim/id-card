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
   
    public DocumentoDTO( UUID codigoPreCadastro, String identificadorDocumento, LocalDate dataEmissao){
        this(codigoPreCadastro, identificadorDocumento, dataEmissao, null, null, null, null, null);
    }

    public DocumentoDTO( UUID codigoPreCadastro, String identificadorDocumento, MultipartFile arquivo){
        this(codigoPreCadastro, identificadorDocumento, null, null, null, null, arquivo, null);
    }

    public DocumentoEntity toDocumentoEntity(){
        final DocumentoEntity entity = TipoDocumento.getInstanceBy(tipo);
       
        entity.setContentType(contentType);
        entity.setDadosArquivo(dadosArquivo);
        entity.setDataEmissao(dataEmissao);
        entity.setIdentificadorDocumento(identificadorDocumento);
        entity.setNomeArquivo(nomeArquivo);

        return entity;
    }

}
