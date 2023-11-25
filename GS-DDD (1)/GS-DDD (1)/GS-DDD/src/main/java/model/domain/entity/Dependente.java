package model.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Dependente {
    private Long id;
    private String nome;
    private String email;
    private Date nascimento;
    private String genero;
    private String cpf;
    private String rg;
    private String telefone;
    private Paciente idTitular;
}
