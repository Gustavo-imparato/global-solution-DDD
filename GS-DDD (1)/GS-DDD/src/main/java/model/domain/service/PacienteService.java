package model.domain.service;


import model.domain.entity.Paciente;
import model.domain.repository.PacienteRepository;

import java.util.List;
import java.util.Objects;

public class PacienteService implements Service<Paciente, Long> {
    private PacienteRepository repository = PacienteRepository.build();

    @Override
    public List<Paciente> findAll() {
        return repository.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Paciente persist(Paciente paciente) {
        if (Objects.nonNull(paciente)){
            return repository.persist(paciente);
        }
        return null;
    }

    @Override
    public Paciente update(Long id, Paciente paciente) {
        return repository.update(paciente);
    }

    @Override
    public boolean delete(Paciente paciente) {
        return repository.delete(paciente);
    }
}