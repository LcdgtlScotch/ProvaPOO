package Controller;

import Model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDados {

    private int idProduto;
    private String nomeProduto;
    private double valorUnitario;
    private String tipoUnidade;
    private int qtdEstoque;

    private Connection con = null;
    private Statement stm = null;
    private ResultSet result = null;
    private Connection connection;

    Produto produto = new Produto();

    public void conecta() {
        String servidor = "jdbc:mysql://localhost:3306/Loja_POO";
        String usuario = "root";
        String senha = "";
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(servidor, usuario, senha);
            this.stm = this.con.createStatement();
        } catch (Exception e) {
            System.out.println("erro na conexao : " + e.getMessage());
        }
    }

    public boolean estaConectado() {
        if (this.con != null) {
            return true;
        } else {
            return false;
        }
    }

    public void seleciona(int id) {
        try {
            String minhaQuery = "SELECT * FROM produtos where idProduto =" + id;
            this.result = this.stm.executeQuery(minhaQuery);
            ResultSet rs = result;
            while (this.result.next()) {

                System.out.println("Cod: " + rs.getString("idProduto")
                        + "|Nome: "
                        + rs.getString("nomeProduto")
                        + "| Preço: "
                        + rs.getInt("valorUnitario")
                        + "| Estoque: "
                        + rs.getInt("qtdEstoque")
                );
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar lista: " + e.getMessage());
        }
    }

    public void inserir(Produto produto) {

        try {

            String nomeProduto = produto.getNomeProduto();
            double valorUnitario = produto.getValorUnitario();
            String tipoUnidade = produto.getTipoUnidade();
            int qtdEstoque = produto.getQtdEstoque();

            String query = "INSERT INTO produtos (nomeProduto,valorUnitario,tipoUnidade,qtdEstoque) VALUES('" + nomeProduto + "','" + valorUnitario + "','" + tipoUnidade + "','" + qtdEstoque + "');";

            this.stm.executeUpdate(query);
            System.out.println("Produto:" + produto.getNomeProduto() + "Incuido com SUCESSO");

        } catch (Exception e) {
            System.out.println("Erro na Inclusao: " + e.getMessage());
        }
    }

    public void editar(int id, int quatCompra) {
        try {
            String query = "UPDATE produtos SET qtdEstoque = qtdEstoque-" + quatCompra + " WHERE idProduto = " + id;
            this.stm.executeUpdate(query);
            System.out.println("Produto adicionado a expedição!");
        } catch (Exception e) {
            System.out.println("Erro na Atualização de Estoque! " + e.getMessage());
        }
    }

    public void excluir(int id) {
        try {
            String query = "DELETE FROM produtos WHERE idProduto = " + id + ";";
            this.stm.executeUpdate(query);
            System.out.println("idProduto: " + id + "EXCLUIDO COM SUCESSO");

        } catch (Exception e) {
            System.out.println("Erro na Exclusao: " + e.getMessage());
        }
    }

    public void listar() {
        try {

            String minhaQuery = "SELECT * FROM produtos ";
            this.result = this.stm.executeQuery(minhaQuery);
            ResultSet rs = result;
            while (this.result.next()) {

                System.out.println(
                        "Cod:--" + rs.getString("idProduto") + "--Nome: " + rs.getString("nomeProduto") + "--Estoque: " + rs.getInt("qtdEstoque") + "--Preço: " + rs.getInt("valorUnitario"));
            }
        } catch (Exception e) {
            System.out.println("Erro na lista: " + e.getMessage());
        }
    }

}
