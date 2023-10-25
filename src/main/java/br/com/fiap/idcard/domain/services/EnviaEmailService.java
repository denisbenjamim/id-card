package br.com.fiap.idcard.domain.services;

import br.com.fiap.idcard.domain.entities.EnviaEmailEntity;
import br.com.fiap.idcard.domain.entities.StatusEnviaEmailPreCadastro;
import br.com.fiap.idcard.domain.repositories.EnviaEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnviaEmailService {

    @Autowired
    private EnviaEmailRepository emailRepository;

    public List<EnviaEmailEntity> findAll(){
        return emailRepository.findAll();
    }

    public List<EnviaEmailEntity> findAllByTipoEnvioEmail(final StatusEnviaEmailPreCadastro tipoEnvioEmail){
        return emailRepository.findAllByTipoEnvioEmail(tipoEnvioEmail);
    }

    public EnviaEmailEntity save(final EnviaEmailEntity enviaEmailEntity){
        return emailRepository.save(enviaEmailEntity);
    }

    public EnviaEmailEntity update(final EnviaEmailEntity enviaEmailEntity){
        return emailRepository.save(enviaEmailEntity);
    }

    public void delete(final Long id){
        emailRepository.deleteById(id);
    }

    public void enviaEmail(EnviaEmailEntity enviaEmailEntity){
        System.out.println("Enviando email para: " + enviaEmailEntity.getPreCadastro().getEmail());
        System.out.println("Tipo de envio: " + enviaEmailEntity.getTipoEnvioEmail());
        System.out.println("Data de envio: " + enviaEmailEntity.getDataHoraEnvio());
        emailRepository.save(enviaEmailEntity);
    }
}
