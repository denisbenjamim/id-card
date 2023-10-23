package br.com.fiap.idcard.domain.services;

import br.com.fiap.idcard.domain.entities.EnviaEmailEntity;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import br.com.fiap.idcard.domain.repositories.EnviaEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnviaEmailService {

    @Autowired
    private EnviaEmailRepository emailRepository;

    public List<EnviaEmailEntity> findAll(){
        return emailRepository.findAll();
    }

    public List<EnviaEmailEntity> findAllByPreCadastro(final UUID uuid){
        return emailRepository.findAllByPreCadastro(uuid);
    }

    public List<EnviaEmailEntity> findAllByTipoEnvioEmail(final StatusPreCadastro tipoEnvioEmail){
        return emailRepository.findAllByTipoEnvioEmail(tipoEnvioEmail);
    }

    public EnviaEmailEntity save(final EnviaEmailEntity entity){
        return emailRepository.save(entity);
    }

    public EnviaEmailEntity update(final EnviaEmailEntity entity){
        return emailRepository.save(entity);
    }

    public void delete(final Long id){
        emailRepository.deleteById(id);
    }
}
