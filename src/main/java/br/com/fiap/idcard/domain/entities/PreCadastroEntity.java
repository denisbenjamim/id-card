package br.com.fiap.idcard.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_pre_cadastro")
public class PreCadastroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="cd_pre_cadastro", unique = true)
    private UUID codigoPreCadastro;

    @Column(name = "nm_pessoa", nullable = false)
    private String nomeCompleto;

    @Column(name="ds_email", nullable = false, unique = true)
    private String email;

    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dataNascimento = LocalDate.now();

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(name = "dt_hr_solicitacao", nullable = false)
    private LocalDateTime dataHoraSolicitacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_status", nullable = true)
    private StatusPreCadastro statusPreCadastro;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_pre_cadastro", nullable = false)
    private List<DocumentoEntity> documentos =  new ArrayList<>();

    @PrePersist
    public void prePersist(){
        statusPreCadastro = StatusPreCadastro.PENDENTE;
        dataHoraSolicitacao = LocalDateTime.now();
    }
}
