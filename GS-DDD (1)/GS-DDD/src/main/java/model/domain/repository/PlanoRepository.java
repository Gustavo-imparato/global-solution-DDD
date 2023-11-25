package model.domain.repository;

import model.domain.entity.Paciente;
import model.domain.entity.Plano;
import model.domain.service.PacienteService;
import model.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class PlanoRepository implements Repository<Plano, Long> {
    private ConnectionFactory factory;
    private static List<Plano> planos;

    static {
        planos = new ArrayList<>();
    }

    private static final AtomicReference<PlanoRepository> instance = new AtomicReference<PlanoRepository>();

    private PlanoRepository() {
        this.factory = ConnectionFactory.build();
    }
    public static PlanoRepository build() {
        instance.compareAndSet(null, new PlanoRepository());
        return instance.get();
    }


    @Override
    public List<Plano> findAll() {
        List<Plano> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "SELECT * FROM T_PS_PLANO";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong("ID_PLANO");
                    String nome = rs.getString("NM_PLANO");
                    Double valor = rs.getDouble("VL_VALOR");
                    String modelo = rs.getString("DS_MODELO");
                    Date inicio = rs.getDate("DT_INICIO");
                    PacienteService pacienteService = new PacienteService();
                    Paciente paciente = pacienteService.findById(id);
                    list.add(new Plano(id, nome, valor, modelo, inicio, paciente));
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
    public Plano findById(Long id) {
        Plano plano = null;
        var sql = "SELECT * FROM T_PS_PLANO where ID_PLANO = ?";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString("NM_PLANO");
                    Double valor = rs.getDouble("VL_VALOR");
                    String modelo = rs.getString("DS_MODELO");
                    Date inicio = rs.getDate("DT_INICIO");
                    PacienteService pacienteService = new PacienteService();
                    Paciente paciente = pacienteService.findById(id);

                    plano = new Plano(id, nome, valor, modelo, inicio, paciente);
                }
            } else {
                System.out.println("Dados não encontrados com o id: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível consultar os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, con);
        }
        return plano;
    }

    @Override
    public Plano persist(Plano plano) {
        var sql = "BEGIN INSERT INTO T_PS_PLANO (NM_PLANO, VL_VALOR, NM_MODELO, DT_INICIO, ID_PACIENTES) VALUES (?,?,?,?,?,?) returning ID_PLANO into ?; END;";

        Connection con = factory.getConnection();
        CallableStatement cs = null;

        try {
            cs = con.prepareCall(sql);
            cs.setString(1, plano.getNome());
            cs.setDouble(2, plano.getValor());
            cs.setString(3, plano.getModelo());
            cs.setDate(4, (Date) plano.getInicio());
            cs.setLong(5, plano.getPacientes().getId());


            cs.registerOutParameter(6, Types.BIGINT);

            cs.executeUpdate();

            plano.setId(cs.getLong(6));

        } catch (SQLException e) {
            System.err.println("Não foi possível inserir os dados!\n" + e.getMessage());
        } finally {
            fecharObjetos(null, cs, con);
        }
        return plano;
    }


    @Override
    public Plano update(Plano plano) {
        return null;
    }

    @Override
    public boolean delete(Plano plano) {
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
