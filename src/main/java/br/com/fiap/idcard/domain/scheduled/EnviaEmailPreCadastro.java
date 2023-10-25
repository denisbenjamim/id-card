package br.com.fiap.idcard.domain.scheduled;

import br.com.fiap.idcard.domain.dto.PreCadastroDTO;
import br.com.fiap.idcard.domain.entities.EnviaEmailEntity;
import br.com.fiap.idcard.domain.entities.StatusEnviaEmailPreCadastro;
import br.com.fiap.idcard.domain.entities.StatusPreCadastro;
import br.com.fiap.idcard.domain.services.EnviaEmailService;
import br.com.fiap.idcard.domain.services.PreCadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EnviaEmailPreCadastro {

        @Autowired
        private PreCadastroService preCadastroService;

        @Autowired
        private EnviaEmailService enviaEmailService;

        private final long SEGUNDO = 1000;
        private final long MINUTO = SEGUNDO * 60;
        private final long HORA = MINUTO * 60;

        @Scheduled(initialDelay = SEGUNDO * 3, fixedDelay = SEGUNDO * 10)
        public void enviarEmail() {
            List<PreCadastroDTO> preCadastrosRevalidar = preCadastroService.getPreCadastroComStatus(StatusPreCadastro.REVALIDAR);
            List<EnviaEmailEntity> emailsEnviadosRevalidar = enviaEmailService.findAllByTipoEnvioEmail(StatusEnviaEmailPreCadastro.REVALIDAR);
            System.out.println("###################### Revalidar ##############################");
            System.out.println("Total preCadastrosRevalidar: " + preCadastrosRevalidar.size());
            System.out.println("Total emailsEnviadosRevalidar: " + emailsEnviadosRevalidar.size());
            List<EnviaEmailEntity> emailsAEnviarRevalidar = getEmailsAEnviar(preCadastrosRevalidar, emailsEnviadosRevalidar, StatusEnviaEmailPreCadastro.REVALIDAR);
            System.out.println("Total emailsAEnviarRevalidar: " + emailsAEnviarRevalidar.size());
            emailsAEnviarRevalidar.forEach(enviaEmailService::enviaEmail);

            List<PreCadastroDTO> preCadastrosValido = preCadastroService.getPreCadastroComStatus(StatusPreCadastro.VALIDADO);
            List<EnviaEmailEntity> emailsEnviadosValido = enviaEmailService.findAllByTipoEnvioEmail(StatusEnviaEmailPreCadastro.VALIDADO);
            System.out.println("###################### Valido ##############################");
            System.out.println("Total preCadastrosValido: " + preCadastrosValido.size());
            System.out.println("Total emailsEnviadosValido: " + emailsEnviadosValido.size());
            List<EnviaEmailEntity> emailsAEnviarValido = getEmailsAEnviar(preCadastrosValido, emailsEnviadosValido, StatusEnviaEmailPreCadastro.VALIDADO);
            System.out.println("Total emailsAEnviarValido: " + emailsAEnviarValido.size());
            emailsAEnviarValido.forEach(enviaEmailService::enviaEmail);
        }

    private static List<EnviaEmailEntity> getEmailsAEnviar(List<PreCadastroDTO> preCadastrosExistentes, List<EnviaEmailEntity> emailsJaEnviados, StatusEnviaEmailPreCadastro statusEnvio) {
        List<EnviaEmailEntity> emailsAEnviar = preCadastrosExistentes.stream()
                .filter(preCadastro -> emailsJaEnviados.stream()
                        .noneMatch(enviaEmail -> enviaEmail.getPreCadastro().getCodigoPreCadastro().equals(preCadastro.codigoPreCadastro())))
                .map(preCadastroDto -> {
                    EnviaEmailEntity enviaEmailEntity = new EnviaEmailEntity();
                    enviaEmailEntity.setPreCadastro(preCadastroDto.toEntity());
                    enviaEmailEntity.setTipoEnvioEmail(statusEnvio);
                    enviaEmailEntity.setDataHoraEnvio(LocalDateTime.now());
                    return enviaEmailEntity;
                }).toList();
        return emailsAEnviar;
    }
}