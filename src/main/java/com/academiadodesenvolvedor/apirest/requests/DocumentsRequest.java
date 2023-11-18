package com.academiadodesenvolvedor.apirest.requests;

import com.academiadodesenvolvedor.apirest.models.Documentos;
import lombok.Data;

@Data
public class DocumentsRequest {
    private boolean cpf;
    private boolean identidade;
    private boolean comprovanteEndereco;
    private boolean certidaoNascimento;

    public Documentos convert() {
        Documentos documentos = new Documentos();
        documentos.setCpf(this.cpf);
        documentos.setIdentidade(this.identidade);
        documentos.setCertidaoNascimento(this.certidaoNascimento);
        documentos.setComprovanteEndereco(this.comprovanteEndereco);
        return documentos;
    }

    public Documentos update(Documentos documentos) {
        documentos.setCpf(this.cpf);
        documentos.setIdentidade(this.identidade);
        documentos.setCertidaoNascimento(this.certidaoNascimento);
        documentos.setComprovanteEndereco(this.comprovanteEndereco);
        return documentos;
    }
}
