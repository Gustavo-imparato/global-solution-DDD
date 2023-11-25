package model.domain.service;

import model.domain.entity.Hospital;
import model.domain.repository.HospitalRepository;

import java.util.List;
import java.util.Objects;

public class HospitalService implements Service<Hospital, Long> {
    private HospitalRepository repository = HospitalRepository.build();

    @Override
    public List<Hospital> findAll() {
        return repository.findAll();
    }

    @Override
    public Hospital findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Hospital persist(Hospital hospital) {
        if (Objects.nonNull(hospital)){
            return repository.persist(hospital);
        }
        return null;
    }

    @Override
    public Hospital update(Long id, Hospital hospital) {
        return repository.update(hospital);
    }

    @Override
    public boolean delete(Hospital hospital) {
        return repository.delete(hospital);
    }
}