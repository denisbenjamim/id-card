package br.com.fiap.idcard.domain.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.idcard.domain.dto.ArquivoDocumentoDTO;
import br.com.fiap.idcard.domain.dto.PreCadastroDTO;
import br.com.fiap.idcard.domain.entities.DocumentoEntity;
import br.com.fiap.idcard.domain.entities.PreCadastroEntity;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import br.com.fiap.idcard.domain.repositories.PreCadastroRepository;

@Service
public class PreCadastroService {

    @Autowired
    private PreCadastroRepository  repository;

    public UUID salvar(PreCadastroDTO dto){
        
        return repository.save(dto.toEntity()).getCodigoPreCadastro();
    }

    public PreCadastroDTO alterar(final PreCadastroEntity entity){
        return repository.save(entity).toDTO();
    }

    public void excluir(final UUID uuid){
        repository.deleteById(uuid);
    }

    public List<PreCadastroDTO> getPreCadastroComStatus(final StatusPreCadastro status){
        return repository.findAllByStatusPreCadastro(status).stream().map(c -> c.toDTO()).collect(Collectors.toList());
    }

    public PreCadastroEntity getPreCadastroByUUID(final UUID uuid){
        return repository.findById(uuid).orElseThrow();
    }

    public void salvarArquivoDocumento(final ArquivoDocumentoDTO documentoDTO){
        final PreCadastroEntity entity = getPreCadastroByUUID(documentoDTO.codigoPreCadastro());

        entity.getDocumentos().forEach(documento -> {
            if(documento.getIdentificadorDocumento().equals(documentoDTO.identificadorDocumento())){
                toArquvivoDocumentoEntity(documento, documentoDTO);
            }
        });
    
        alterar(entity);
    }

    public void toArquvivoDocumentoEntity(final DocumentoEntity documentoEntity, final ArquivoDocumentoDTO documentoDTO){
      
        try{
            documentoEntity.setNomeArquivo( documentoDTO.arquivo().getOriginalFilename());
            documentoEntity.setContentType( documentoDTO.arquivo().getContentType());        
            documentoEntity.setDadosArquivo( documentoDTO.arquivo().getBytes());
        } catch(IOException exception){
            throw new RuntimeException(exception);
        }
    }
}
