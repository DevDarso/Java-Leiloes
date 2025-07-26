import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    private Connection conexao;

    
    public conectaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public conectaDAO() {
    }
    
    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public void conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?user=root&password=Devdarso2025");
            System.out.println("Sucesso na conexão");
        } catch (ClassNotFoundException cn) {
            System.out.println("Falha ao conectar com banco" + cn);
        } catch (SQLException sql) {
            System.out.println("Erro de SQL" + sql);
        }
    }

    public void desconectar() {
        try {
            if (conexao != null) {
                conexao.close();
                System.out.println("Desconectado");
            }
        } catch (SQLException sql) {
            System.out.println("Erro ao desconectar do banco de dados" + sql);
        }
    }
}
