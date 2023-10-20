package br.com.fiap.idcard.domain.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.idcard.domain.entities.PreCadastroEntity;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;

@Repository
public interface PreCadastroRepository extends JpaRepository<PreCadastroEntity, UUID> {
    public List<PreCadastroEntity> findAllByStatusPreCadastro(StatusPreCadastro status);
}
