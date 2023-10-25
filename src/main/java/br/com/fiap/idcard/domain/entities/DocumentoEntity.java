package br.com.fiap.idcard.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import br.com.fiap.idcard.domain.dto.DocumentoDTO;
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

    public DocumentoDTO toDTO(final UUID codigoPreCadastro, final String tipoDocumento){
        return new DocumentoDTO(
            codigoPreCadastro, 
            getIdentificadorDocumento(), 
            getDataEmissao(),
            tipoDocumento
        );
    }

    public String getTipoArquivoPeloNomeDaClasse() {
        return this.getClass().getSimpleName().replace("Entity", "");
    }
}
