package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    public static void main(String[] args) {
        ConnectionClass instancja =new ConnectionClass();
        instancja.createConnection();
    }
    void createConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
            Connection con = DriverManager.getConnection("jdbc:mysql:data.sql");
            System.out.println("ok");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
