package model.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Hospital {
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String cep;
    private String endereco;
    private String complemento;
}
