package br.com.fiap.idcard.domain.repositories;

import br.com.fiap.idcard.domain.entities.EnviaEmailEntity;
import br.com.fiap.idcard.domain.entities.StatusEnviaEmailPreCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnviaEmailRepository extends JpaRepository<EnviaEmailEntity, Long> {
    public List<EnviaEmailEntity> findAllByTipoEnvioEmail(StatusEnviaEmailPreCadastro tipoEnvioEmail);
}