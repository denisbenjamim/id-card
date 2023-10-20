package br.com.fiap.idcard.domain.controller.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import br.com.fiap.idcard.domain.entities.StatusPreCadastro;

@Component
public class StatusPreCadastroConverter implements Converter<String, StatusPreCadastro> {

    @Override
    @Nullable
    public StatusPreCadastro convert(String status) {
       if(status == null || status.isEmpty()){
          return null;
       }

       return StatusPreCadastro.valueOf(status.toUpperCase());
    }
    
}
