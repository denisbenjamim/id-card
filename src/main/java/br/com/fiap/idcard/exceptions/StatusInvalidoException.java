package br.com.fiap.idcard.exceptions;

import br.com.fiap.idcard.infra.Utils;

public class StatusInvalidoException extends PreCadastroException {
    public StatusInvalidoException(){
        super(Utils.getMessage("status.informado.invalido"));
    }
}
