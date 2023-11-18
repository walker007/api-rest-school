package com.academiadodesenvolvedor.apirest.dtos;

import com.academiadodesenvolvedor.apirest.models.Documentos;
import lombok.Data;

@Data
public class DocumentosDTO {
    private boolean cpf;
    private boolean identidade;
    private boolean comprovanteEndereco;
    private boolean certidaoNascimento;

    public DocumentosDTO(Documentos documentos) {
        this.cpf = documentos.isCpf();
        this.certidaoNascimento = documentos.isCertidaoNascimento();
        this.comprovanteEndereco = documentos.isComprovanteEndereco();
        this.identidade = documentos.isIdentidade();
    }
}
