package model.domain.dto;


import model.domain.entity.Paciente;
import model.domain.entity.Plano;
import model.domain.service.PlanoService;

import java.util.Date;
import java.util.Objects;

public record PlanoDTO(Long id, String nome, Double valor, String modelo, Date inicio, Paciente pacientes) {
    private static PlanoService service = new PlanoService();

    public static Plano of(PlanoDTO dto) {

        if (Objects.isNull(dto))
            return null;

        if (Objects.nonNull(dto.id))
            return service.findById(dto.id);

        Plano plano = new Plano();
        plano.setId(null);
        plano.setNome(dto.nome);
        plano.setValor(dto.valor);
        plano.setModelo(dto.modelo);
        plano.setInicio(dto.inicio);
        plano.setPacientes(dto.pacientes);
        return plano;
    }
}
