package br.com.fiap.idcard.domain.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.idcard.domain.controller.dto.ArquivoDocumentoDTO;
import br.com.fiap.idcard.domain.entities.PreCadastroEntity;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import br.com.fiap.idcard.domain.repositories.PreCadastroRepository;

@Service
public class PreCadastroService {

    @Autowired
    private PreCadastroRepository  repository;

    public PreCadastroEntity salvar(final PreCadastroEntity entity){
        return repository.save(entity);
    }

    public PreCadastroEntity alterar(final PreCadastroEntity entity){
        return repository.save(entity);
    }

    public void excluir(final UUID uuid){
        repository.deleteById(uuid);
    }

    public List<PreCadastroEntity> getPreCadastroComStatus(final StatusPreCadastro status){
        return repository.findAllByStatusPreCadastro(status);
    }

    public PreCadastroEntity getPreCadastroByUUID(final UUID uuid){
        return repository.findById(uuid).orElseThrow();
    }

    public void salvarArquivoDocumento(final ArquivoDocumentoDTO documentosDTO){
       
        final PreCadastroEntity entity = getPreCadastroByUUID(documentosDTO.codigoPreCadastro());
    
        entity.getDocumentos().forEach(documento -> {
            if(documento.getIdentificadorDocumento().equals(documentosDTO.identificadorDocumento())){
                documento.setArquivo(documentosDTO.arquivo());
            }
        });
    
        alterar(entity);
    }
}
