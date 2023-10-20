package br.com.fiap.idcard.domain.entities;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"
)
@JsonSubTypes({
    @Type(value = CPFEntity.class, name="CPF"),
    @Type(value = RGEntity.class, name="RG"),
    @Type(value = CNHEntity.class, name="CNH"),
})
@Data
@EqualsAndHashCode(of = "identificadorDocumento")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tb_documento")
public abstract class DocumentoEntity {
    @Id
    @Column(name = "cd_documento")
    private String identificadorDocumento;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name ="dt_emissao")
    private LocalDate dataEmissao;

    @Column(columnDefinition = "BLOB", name = "dd_arquivo")
    private byte[] dadosArquivo;
    
    @Column(name = "nm_arquivo")
    private String nomeArquivo;
    
    @Column(name = "tp_content")
    private String contentType;

    public void setArquivo(MultipartFile multipartFile){
        setNomeArquivo(multipartFile.getOriginalFilename());
        setContentType(multipartFile.getContentType());
        
        try{
            setDadosArquivo(multipartFile.getBytes());
        } catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }

}
