package model.domain.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Plano {
    private Long id;
    private String nome;
    private Double valor;
    private String modelo;
    private Date inicio;
    private Paciente pacientes;
}
