import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto) throws ClassNotFoundException{
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
        conectaDAO conn = null;
        PreparedStatement ps = null;

        try {
            conn = new conectaDAO();
            conn.conectar();
            ps = conn.getConexao().prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getValor());
            ps.setString(3, produto.getStatus());
           
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
               // System.out.println("Produto adicionado com sucesso!");
//                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao adicionar no banco de dados: " + ex.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.desconectar();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return false;
    }
 
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conectaDAO conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = new conectaDAO();
            conexao.conectar();
            String sql = "SELECT * FROM produtos";
            ps = conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException se) {
            System.err.println("Erro ao listar serviços: " + se.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexao != null) conexao.desconectar();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listagem;
    }    
       public static boolean atualizarStatus(int id) {
    conectaDAO conexao = null;
    PreparedStatement ps = null;

    try {
        conexao = new conectaDAO();
        conexao.conectar();

        String sql = "UPDATE produtos SET status = 'Vendido' " +
             "WHERE id = ? ";

        ps = conexao.getConexao().prepareStatement(sql);
        ps.setInt(1, id);

        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Erro ao atualizar status do produto: " + e.getMessage());
        return false;
    }
}
       public ArrayList<ProdutosDTO> listarVendidos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conectaDAO conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = new conectaDAO();
            conexao.conectar();
            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            ps = conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException se) {
            System.err.println("Erro ao listar serviços: " + se.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexao != null) conexao.desconectar();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
        return listagem;
    }   
 }

