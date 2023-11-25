package model.domain.dto;

import model.domain.entity.Hospital;
import model.domain.service.HospitalService;

import java.util.Objects;

public record HospitalDTO(Long id, String nome, String cnpj, String telefone, String cep, String endereco, String complemento) {

    private static HospitalService service = new HospitalService();

    public static Hospital of(HospitalDTO dto) {

        if (Objects.isNull(dto))
            return null;

        if (Objects.nonNull(dto.id))
            return service.findById(dto.id);

        Hospital hospital = new Hospital();
        hospital.setId(null);
        hospital.setNome(dto.nome);
        hospital.setCnpj(dto.cnpj);
        hospital.setTelefone(dto.telefone);
        hospital.setCep(dto.cep);
        hospital.setEndereco(dto.endereco);
        hospital.setComplemento(dto.complemento);
        return hospital;
    }
}
