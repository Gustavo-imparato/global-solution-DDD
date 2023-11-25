package model.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Paciente {
    private Long id;
    private String nome;
    private String email;
    private Date nascimento;
    private String genero;
    private String cpf;
    private String rg;
    private String telefone;
    private String cep;
    private String endereco;
    private String complemento;
    private Set<Dependente> dependentes = new LinkedHashSet<>();
}

