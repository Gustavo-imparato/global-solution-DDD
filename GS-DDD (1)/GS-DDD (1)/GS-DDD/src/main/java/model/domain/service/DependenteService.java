package model.domain.service;


import model.domain.entity.Dependente;
import model.domain.repository.DependenteRepository;

import java.util.List;
import java.util.Objects;

public class DependenteService implements  Service<Dependente, Long>{
    private DependenteRepository repository;

    public DependenteService() {
        repository = DependenteRepository.build();
    }

    @Override
    public List<Dependente> findAll() {
        return repository.findAll();
    }

    @Override
    public Dependente findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Dependente persist(Dependente dependente) {
        if (Objects.nonNull(dependente)) {
            return repository.persist(dependente);
        }
        return null;
    }

    @Override
    public Dependente update(Long id, Dependente dependente) {
        return repository.update(dependente);
    }

    @Override
    public boolean delete(Dependente dependente) {
        return repository.delete(dependente);
    }


}