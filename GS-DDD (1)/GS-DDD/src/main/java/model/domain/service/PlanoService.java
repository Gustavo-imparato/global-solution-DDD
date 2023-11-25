package model.domain.service;

import model.domain.entity.Dependente;
import model.domain.entity.Plano;
import model.domain.repository.PlanoRepository;

import java.util.List;
import java.util.Objects;

public class PlanoService implements  Service<Plano, Long>{
    private PlanoRepository repository;

    public PlanoService() {
        repository = PlanoRepository.build();
    }

    @Override
    public List<Plano> findAll() {
        return repository.findAll();
    }

    @Override
    public Plano findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Plano persist(Plano plano) {
        if (Objects.nonNull(plano)) {
            return repository.persist(plano);
        }
        return null;
    }

    @Override
    public Plano update(Long id, Plano plano) {
        return repository.update(plano);
    }

    @Override
    public boolean delete(Plano plano) {
        return repository.delete(plano);
    }


}
