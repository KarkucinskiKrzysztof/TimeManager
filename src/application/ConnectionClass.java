package application;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;

public class ConnectionClass {
    public final static ConnectionClass CON = new ConnectionClass();

    public static void main(String[] args) {
        ConnectionClass inst = new ConnectionClass();
//        inst.categoryInsert("KolorUG", "234234", "red", 23);
        //inst.listElem("SELECT DISTINCT category.name FROM category");
        inst.selectFromDB("SELECT DISTINCT category.name FROM category");

    }

// TODO: 2017-11-09  pozamykać połączenia


    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "\";jdbc:mysql://localhost:3306/mydb\",\"root\",\"root\"";

    private Connection conn;
    private Statement stat;
    private PreparedStatement prepStmt;

    private ResultSet result;
    private PreparedStatement categoryInstertPS;
    private PreparedStatement projectInsertPS;
    private PreparedStatement categoryDeletePS;
    private PreparedStatement projectDeletePS;
    private PreparedStatement durationInstertPS;
    private PreparedStatement checkCategoryPS;

    public ConnectionClass() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
            stat = conn.createStatement();

            categoryInstertPS = conn.prepareStatement(
                    "INSERT INTO category VALUES (NULL, ?, ?, ?, ?);");
            projectInsertPS = conn.prepareStatement(
                    "INSERT INTO projects VALUES (NULL, ?, ?, ?, ?, ?);");
            categoryDeletePS = conn.prepareStatement(
                    "DELETE FROM category WHERE idcategory = ?");
            projectDeletePS = conn.prepareStatement(
                    "DELETE FROM projects WHERE idprojects = ?");
            durationInstertPS = conn.prepareStatement(
                    "INSERT INTO durations VALUES (NULL, ?, ?, ?, ?, ?,?);");
            checkCategoryPS = conn.prepareStatement("SELECT DISTINCT category.name FROM ?");

        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
        }
        //dataBaseInitiator();
    }

    public void categoryInsert(String p1, String p2, String p3, int p4) {
        try {
            categoryInstertPS.setString(1, p1);
            categoryInstertPS.setString(2, p2);
            categoryInstertPS.setString(3, p3);
            categoryInstertPS.setInt(4, p4);
            categoryInstertPS.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy wstawianiu czytelnika");
        }

    }



    public List<String> selectFromDB(String command) {
        List<String> selectedElements = new ArrayList<>(20);
        try {
            result = stat.executeQuery(command);
            String name;
            while (result.next()) {
                name = result.getString("name");
                selectedElements.add(name);
            }
        } catch (SQLException e) {
            System.err.println("Blad przy pobieraniu danych");
        }
        return selectedElements;
    }

}

//---------------------------------------------------------------------
//   DZIAŁA ALE JEST ZA DŁUGA
//
//        public ArrayList<String> selectFromDB2(String command) {
//        ArrayList<String> lista = new ArrayList<>();
//        try {
//            Class.forName(DRIVER);
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
//            stat = conn.createStatement();
//            ResultSet rs = stat.executeQuery(command);
//            while (rs.next()) {
//                String name = rs.getString("name");
//                lista.add(name);
//                System.out.println(name);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return lista;
//    }
//--------------------------------------------------------------
//  NIE DZIALA
//
//    public ArrayList<String> listElem(String nameOfTable) {
//        ArrayList<String> lista = new ArrayList<>();
//        try {
//            checkCategoryPS.setString(1, nameOfTable);
//            ResultSet rs = checkCategoryPS.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString("name");
//                lista.add(name);
//               // System.out.println(name);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return lista;
//    }
//}
//
//
//--------------------------------------------------------------
// Różne
//    public static void main(String[] args) {
//        ConnectionClass instancja = new ConnectionClass();
//        instancja.createConnection();
//    }
//    void createConnection(){
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM category");
//            while(rs.next()){
//                String name = rs.getString("name");
//                String color = rs.getString("color");
//                String discription = rs.getString("description");
//                System.out.println(name+" "+color+" "+discription);
//            }
//
//                    System.out.println("ok");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

