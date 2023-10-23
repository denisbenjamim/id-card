package br.com.fiap.idcard.domain.repositories;

import br.com.fiap.idcard.domain.entities.EnviaEmailEntity;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnviaEmailRepository extends JpaRepository<EnviaEmailEntity, Long> {
    public List<EnviaEmailEntity> findAllByTipoEnvioEmail(StatusPreCadastro tipoEnvioEmail);
    public List<EnviaEmailEntity> findAllByPreCadastro(UUID uuid);
}