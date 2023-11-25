package model.domain.dto;

import model.domain.entity.Dependente;
import model.domain.entity.Paciente;
import model.domain.service.PacienteService;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public record PacienteDTO(Long id, String nome, String email, Date nascimento, String genero, String cpf, String rg, String telefone, String cep, String endereco, String complemento, Set<Dependente> dependentes) {
    private static PacienteService service = new PacienteService();

    public static Paciente of(PacienteDTO dto) {

        if (Objects.isNull(dto))
            return null;

        if (Objects.nonNull(dto.id))
            return service.findById(dto.id);

        Paciente paciente = new Paciente();
        paciente.setId(null);
        paciente.setNome(dto.nome);
        paciente.setEmail(dto.email);
        paciente.setNascimento(dto.nascimento);
        paciente.setGenero(dto.genero);
        paciente.setCpf(dto.cpf);
        paciente.setRg(dto.rg);
        paciente.setTelefone(dto.telefone);
        paciente.setCep(dto.cep);
        paciente.setEndereco(dto.endereco);
        paciente.setComplemento(dto.complemento);
        return paciente;
    }
}
