package br.com.fiap.idcard.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PreCadastroException extends RuntimeException {
    public PreCadastroException(final String mensagem){
        super(mensagem);
    }
}
