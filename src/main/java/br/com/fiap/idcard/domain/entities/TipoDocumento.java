package br.com.fiap.idcard.domain.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.com.fiap.idcard.infra.Utils;

import java.lang.IllegalArgumentException;

public enum TipoDocumento {
    CPF {
        @Override
        public DocumentoEntity getInstance() {
           return new CPFEntity();
        }
    }, 
    RG {
        @Override
        public DocumentoEntity getInstance() {
            return new RGEntity();
        }
    }, 
    CNH {
        @Override
        public DocumentoEntity getInstance() {
            return new CNHEntity();
        }
    };

    public static Map<String,TipoDocumento> map = new HashMap<>();
    
    static {
        Arrays.asList(TipoDocumento.values()).forEach(t -> map.put(t.name(), t) );
    }

    public abstract DocumentoEntity getInstance();

    public static DocumentoEntity getInstanceBy(final String name){
        if(name == null){
            throw new IllegalArgumentException(Utils.getMessage("argumento.invalido"));
        }

        final TipoDocumento tipoDocumento = map.get(name.toUpperCase());
        
        if( tipoDocumento == null){
            return null;
        }

        return tipoDocumento.getInstance();
    }
}
