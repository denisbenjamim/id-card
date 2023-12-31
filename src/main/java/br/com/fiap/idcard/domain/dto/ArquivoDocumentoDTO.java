package br.com.fiap.idcard.domain.dto;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public record ArquivoDocumentoDTO (
    UUID codigoPreCadastro,
    String identificadorDocumento,
    MultipartFile arquivo
) {}

