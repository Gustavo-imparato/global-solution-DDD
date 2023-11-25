package model.domain.repository;
import model.domain.entity.Dependente;
import model.domain.entity.Paciente;
import model.domain.service.PacienteService;
import model.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class DependenteRepository implements Repository<Dependente, Long> {
    private ConnectionFactory factory;

    private static final AtomicReference<DependenteRepository> instance = new AtomicReference<>();

    private DependenteRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static DependenteRepository build() {
        instance.compareAndSet(null, new DependenteRepository());
        return instance.get();
    }
    @Override
    public List<Dependente> findAll() {
        List<Dependente> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT * FROM T_PS_DEPENDENTE";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong("ID_DEPENDENTE");
                    String nome = rs.getString("NM_DEPENDENTE");
                    String email = rs.getString("DS_EMAIL");
                    Date nascimento = rs.getDate("DT_NASCIMENTO");
                    String genero = rs.getString("DS_GENERO");
                    String cpf = rs.getString("NR_CPF");
                    String rg = rs.getString("NR_RG");
                    String telefone = rs.getString("NR_TELEFONE");
                    PacienteService pacienteService = new PacienteService();
                    Paciente idTitular = pacienteService.findById(id);


                    list.add(new Dependente(id, nome, email, nascimento, genero, cpf, rg, telefone, idTitular));
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(rs, st, con);
        }
        return list;
    }
    @Override
    public Dependente findById(Long id) {
        Dependente pet = null;
        var sql = "SELECT * FROM T_PS_DEPENDENTE where ID_DEPENDENTE = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dependente dependente = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString("NM_DEPENDENTE");
                    String email = rs.getString("DS_EMAIL");
                    Date nascimento = rs.getDate("DT_NASCIMENTO");
                    String genero = rs.getString("DS_GENERO");
                    String cpf = rs.getString("NR_CPF");
                    String rg = rs.getString("NR_RG");
                    String telefone = rs.getString("NR_TELEFONE");
                    PacienteService pacienteService = new PacienteService();
                    Paciente idTitular = pacienteService.findById(id);
                    dependente = new Dependente(id, nome, email, nascimento, genero, cpf, rg, telefone, idTitular);

                }
            } else {
                System.out.println("Dados não encontrados com o id: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, con);
        }
        return dependente;
    }


    @Override
    public Dependente persist(Dependente dependente) {
        var sql = "BEGIN INSERT INTO TB_BIKE (NM_PACIENTE, DS_EMAIL, DT_NASCIMENTO, DS_GENERO, NR_CPF, NR_RG, NR_TELEFONE, NR_CEP) VALUES (?,?,?,?,?,?,?,?) returning ID_DEPENDENTE into ?; END;";

        Connection con = factory.getConnection();
        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.setString(1, dependente.getNome());
            cs.setString(2, dependente.getEmail());
            cs.setDate(3, (Date) dependente.getNascimento());
            cs.setString(4, dependente.getGenero());
            cs.setString(5, dependente.getCpf());
            cs.setString(6, dependente.getRg());
            cs.setString(7, dependente.getTelefone());

            cs.registerOutParameter(8, Types.BIGINT);

            cs.executeUpdate();

            dependente.setId(cs.getLong(8));

        } catch (SQLException e) {
            System.err.println("Não foi possível inserir os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(null, cs, con);
        }
        return dependente;
    }



    @Override
    public Dependente update(Dependente dependente) {
        return null;
    }

    @Override
    public boolean delete(Dependente dependente) {
        return false;
    }

    private static void fecharObjetos(ResultSet rs, Statement st, Connection con) {
        try {
            if (Objects.nonNull(rs) && !rs.isClosed()) {
                rs.close();
            }
            st.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Erro ao encerrar o ResultSet, a Connection e o Statment!\n" + e.getMessage());
        }
    }
}