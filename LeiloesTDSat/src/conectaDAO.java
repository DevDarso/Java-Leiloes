import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    private Connection conexao;

    // Construtor para inicializar a conexão
    public conectaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Construtor sem parâmetros
    public conectaDAO() {
    }

    // Método para obter a conexão
    public Connection getConexao() {
        return conexao;
    }

    // Método para configurar a conexão
    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    // Método para conectar ao banco de dados
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

    // Método para desconectar do banco de dados
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
