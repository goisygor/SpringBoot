package webapp.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Email {
    //atributo
    private Connection connection;

    //construtor
    public Email(){
        this.connection = ConnectionFactory.getConnection();
    }

     //criar Tabela
    public void criaTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS newsletter_spring (ID SERIAL PRIMARY KEY, EMAIL    VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }
}