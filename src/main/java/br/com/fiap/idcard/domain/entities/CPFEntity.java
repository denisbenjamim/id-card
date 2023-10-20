package br.com.fiap.idcard.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="tb_documento_cpf")
public class CPFEntity extends DocumentoEntity {
    
}
