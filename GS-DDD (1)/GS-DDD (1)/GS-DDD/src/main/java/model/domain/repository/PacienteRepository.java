package model.domain.repository;


import model.domain.entity.Dependente;
import model.domain.entity.Paciente;
import model.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class PacienteRepository implements Repository<Paciente, Long> {
    private ConnectionFactory factory;
    private static List<Paciente> pacientes;

    static {
        pacientes = new ArrayList<>();
    }

    private static final AtomicReference<PacienteRepository> instance = new AtomicReference<>();

    private PacienteRepository() {
        this.factory = ConnectionFactory.build();
    }
    public static PacienteRepository build() {
        instance.compareAndSet(null, new PacienteRepository());
        return instance.get();
    }


    @Override
    public List<Paciente> findAll() {
        List<Paciente> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT * FROM T_PS_PACIENTE";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong("ID_PACIENTE");
                    String nome = rs.getString("NM_PACIENTE");
                    String email = rs.getString("DS_EMAIL");
                    Date nascimento = rs.getDate("DT_NASCIMENTO");
                    String genero = rs.getString("DS_GENERO");
                    String cpf = rs.getString("NR_CPF");
                    String rg = rs.getString("NR_RG");
                    String telefone = rs.getString("NR_TELEFONE");
                    String cep = rs.getString("NR_CEP");
                    String endereco = rs.getString("DS_ENDERECO");
                    String complemento = rs.getString("DS_COMPLEMENTO");
                    Set<Dependente> dependentes = null;
                    list.add(new Paciente(id, nome, email, nascimento, genero, cpf, rg, telefone, cep, endereco, complemento, dependentes));
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
    public Paciente findById(Long id) {
        Paciente paciente = null;
        var sql = "SELECT * FROM T_PS_PACIENTE where ID_PACIENTE = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString("NM_PACIENTE");
                    String email = rs.getString("DS_EMAIL");
                    Date nascimento = rs.getDate("DT_NASCIMENTO");
                    String genero = rs.getString("DS_GENERO");
                    String cpf = rs.getString("NR_CPF");
                    String rg = rs.getString("NR_RG");
                    String telefone = rs.getString("NR_TELEFONE");
                    String cep = rs.getString("NR_CEP");
                    String endereco = rs.getString("DS_ENDERECO");
                    String complemento = rs.getString("DS_COMPLEMENTO");
                    Set<Dependente> dependentes = null;

                    paciente = new Paciente(id, nome, email, nascimento, genero, cpf, rg, telefone, cep, endereco, complemento, dependentes);
                }
            } else {
                System.out.println("Dados não encontrados com o id: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, con);
        }
        return paciente;
    }

    @Override
    public Paciente persist(Paciente paciente) {
        var sql = "BEGIN INSERT INTO T_PS_PACIENTE (NM_PACIENTE, DS_EMAIL, DT_NASCIMENTO, DS_GENERO, NR_CPF, NR_RG, NR_TELEFONE, NR_CEP, DS_ENDERECO, DS_COMPLEMENTO) VALUES (?,?,?,?,?,?,?,?,?,?,?) returning ID_PACIENTE into ?; END;";

        Connection con = factory.getConnection();
        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.setString(1, paciente.getNome());
            cs.setString(2, paciente.getEmail());
            cs.setDate(3, (Date) paciente.getNascimento());
            cs.setString(4, paciente.getGenero());
            cs.setString(5, paciente.getCpf());
            cs.setString(6, paciente.getRg());
            cs.setString(7, paciente.getTelefone());
            cs.setString(8, paciente.getCep());
            cs.setString(9, paciente.getEndereco());
            cs.setString(10, paciente.getComplemento());

            cs.registerOutParameter(11, Types.BIGINT);

            cs.executeUpdate();

            paciente.setId(cs.getLong(11));

        } catch (SQLException e) {
            System.err.println("Não foi possível inserir os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(null, cs, con);
        }
        return paciente;
    }


    @Override
    public Paciente update(Paciente paciente) {
        return null;
    }

    @Override
    public boolean delete(Paciente paciente) {
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