package br.com.fiap.idcard.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_envia_email")
public class EnviaEmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cd_pre_cadastro", nullable = false)
    private PreCadastroEntity preCadastro;

    @Enumerated(EnumType.STRING)
    private StatusPreCadastro tipoEnvioEmail;
    private LocalDateTime dataHoraEnvio;

    @PrePersist
    public void prePersist(){
        dataHoraEnvio = LocalDateTime.now();
    }
}
