package db;
// Define o pacote onde essa classe está organizada

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
// Importações necessárias para trabalhar com arquivos, conexão com banco e propriedades

public class DB {
    // Classe responsável por gerenciar a conexão com o banco de dados

    public static Connection conn = null;
    // Variável estática que armazena a conexão (compartilhada por toda a aplicação)

    public static Connection getConnection() {
        // Método público que retorna uma conexão com o banco

        if (conn == null) {
            // Verifica se ainda não existe conexão aberta

            try {
                Properties props = loadProperties();
                // Carrega as propriedades do arquivo db.properties

                String url = props.getProperty("dburl");
                // Pega a URL do banco de dados do arquivo

                conn = DriverManager.getConnection(url, props);
                // Cria a conexão usando a URL e as propriedades (usuário, senha, etc)

            } catch (SQLException e) {
                // Trata erro de conexão com banco

                throw new DBException(e.getMessage());
                // Lança uma exceção personalizada com a mensagem do erro
            }
        }

        return conn;

    }
    public static void closeConnection(){

        if(conn != null);// eu posso colocar qualquer nome aqui pra essas conexão
        try {
            conn.close();//operacao de fechar uma conexao com o banco

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Properties loadProperties() {
        // Método responsável por carregar o arquivo de configuração

        try (FileInputStream fs = new FileInputStream("db.properties")) {
            // Abre o arquivo db.properties

            Properties props = new Properties();
            // Cria um objeto para armazenar as propriedades

            props.load(fs);
            // Carrega os dados do arquivo para o objeto

            return props;
            // Retorna as propriedades carregadas

        } catch (IOException e) {
            // Trata erro ao ler o arquivo

            throw new DBException(e.getMessage());
            // Lança exceção personalizada
        }
    }
}