package model.domain.dto;

import model.domain.entity.Dependente;
import model.domain.service.DependenteService;

import java.util.Date;
import java.util.Objects;

public record DependenteDTO(Long id, String nome, String email, Date nascimento, String genero, String cpf, String rg, String telefone) {
    private static DependenteService service = new DependenteService();

    public static Dependente of(DependenteDTO dto) {

        if (Objects.isNull(dto))
            return null;

        if (Objects.nonNull(dto.id))
            return service.findById(dto.id);

        Dependente dependente = new Dependente();
        dependente.setId(null);
        dependente.setNome(dto.nome);
        dependente.setEmail(dto.email);
        dependente.setNascimento(dto.nascimento);
        dependente.setGenero(dto.genero);
        dependente.setCpf(dto.cpf);
        dependente.setRg(dto.rg);
        dependente.setTelefone(dto.telefone);
        return dependente;
    }
}
