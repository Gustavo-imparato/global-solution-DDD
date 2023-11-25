package model.domain.repository;

import model.domain.entity.Dependente;
import model.domain.entity.Hospital;
import model.domain.entity.Paciente;
import model.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class HospitalRepository implements Repository<Hospital, Long> {
    private ConnectionFactory factory;
    private static List<Hospital> hospitais;

    static {
        hospitais = new ArrayList<>();
    }

    private static final AtomicReference<HospitalRepository> instance = new AtomicReference<>();

    private HospitalRepository() {
        this.factory = ConnectionFactory.build();
    }
    public static HospitalRepository build() {
        instance.compareAndSet(null, new HospitalRepository());
        return instance.get();
    }


    @Override
    public List<Hospital> findAll() {
        List<Hospital> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT * FROM T_PS_HOSPITAL";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong("ID_HOSPITAL");
                    String nome = rs.getString("NM_HOSPITAL");
                    String cnpj = rs.getString("NR_CNPJ");
                    String telefone = rs.getString("NR_TELEFONE");
                    String cep = rs.getString("NR_CEP");
                    String endereco = rs.getString("DS_ENDERECO");
                    String complemento = rs.getString("DS_COMPLEMENTO");
                    list.add(new Hospital(id, nome, cnpj, telefone, cep, endereco, complemento));
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
    public Hospital findById(Long id) {
        Hospital hospital = null;
        var sql = "SELECT * FROM T_PS_HOSPITAL where ID_HOSPITAL = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString("NM_HOSPITAL");
                    String cnpj = rs.getString("NR_CNPJ");
                    String telefone = rs.getString("NR_TELEFONE");
                    String cep = rs.getString("NR_CEP");
                    String endereco = rs.getString("DS_ENDERECO");
                    String complemento = rs.getString("DS_COMPLEMENTO");
                    hospital = new Hospital(id, nome, cnpj, telefone, cep, endereco, complemento);
                }
            } else {
                System.out.println("Dados não encontrados com o id: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, con);
        }
        return hospital;
    }

    @Override
    public Hospital persist(Hospital hospital) {
        var sql = "BEGIN INSERT INTO T_PS_HOSPITAL (NM_HOSPITAL, NR_CNPJ, NR_TELEFONE, NR_CEP, DS_ENDERECO, DS_COMPLEMENTO) VALUES (?,?,?,?,?,?,?) returning ID_PACIENTE into ?; END;";

        Connection con = factory.getConnection();
        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.setString(1, hospital.getNome());
            cs.setString(2, hospital.getCnpj());
            cs.setString(3, hospital.getTelefone());
            cs.setString(4, hospital.getCep());
            cs.setString(5, hospital.getEndereco());
            cs.setString(6, hospital.getComplemento());

            cs.registerOutParameter(7, Types.BIGINT);

            cs.executeUpdate();

            hospital.setId(cs.getLong(7));

        } catch (SQLException e) {
            System.err.println("Não foi possível inserir os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(null, cs, con);
        }
        return hospital;
    }


    @Override
    public Hospital update(Hospital hospital) {
        return null;
    }

    @Override
    public boolean delete(Hospital hospital) {
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
