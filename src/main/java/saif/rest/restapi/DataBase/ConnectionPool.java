package saif.rest.restapi.DataBase;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
    private static Connection con;

    private ConnectionPool(){}

    public static Connection getConnection() throws Exception{
        if(con == null){
            Dotenv dotenv = Dotenv.configure().load();
            con = DriverManager.getConnection("jdbc:mysql://localhost:"+ dotenv.get("PORT")+"/"+dotenv.get("DATABASE_NAME")+"?useSSL=false&serverTimezone=UTC",dotenv.get("DATABASE_USER"),dotenv.get("DATABASE_PASSWORD"));
            return con;
        }
        return con;
    }
}
