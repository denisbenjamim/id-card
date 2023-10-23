package br.com.fiap.idcard.domain.scheduled;

import br.com.fiap.idcard.domain.entities.PreCadastroEntity;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import br.com.fiap.idcard.domain.services.PreCadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnviaEmailPreCadastro {

        @Autowired
        private PreCadastroService preCadastroService;

        private final long SEGUNDO = 1000;
        private final long MINUTO = SEGUNDO * 60;
        private final long HORA = MINUTO * 60;

        @Scheduled(initialDelay = 2000, fixedDelay = HORA)
        public void enviarEmail() {
            List<PreCadastroEntity> preCadastrosRevalidar = preCadastroService.getPreCadastroComStatus(StatusPreCadastro.REVALIDAR);
            List<PreCadastroEntity> preCadastrosValido = preCadastroService.getPreCadastroComStatus(StatusPreCadastro.VALIDADO);
            System.out.println("Enviando email de pré-cadastro...");
            System.out.println("Pre-cadastros revalidar: " + preCadastrosRevalidar);
            System.out.println("Pre-cadastros valido: " + preCadastrosValido);
        }
}
