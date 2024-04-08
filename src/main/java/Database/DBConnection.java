package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
  private static Connection connection = null;

  public static Connection getConnection(){
    try{
      if (connection == null){
        // TODO = C:\Dev\tutorial java 2\Adv OOP Asgn\foodstore.db
        // You must use the folder path that suits your laptop where
        // you save the foodstore.db file on your laptop.
        String jdbcURL = "jdbc:sqlite:C:/Users/44749/ec/foodstore.db";
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(jdbcURL);
        System.out.println("Connected");
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return connection;
  }

  public static void main(String[] args) {
    getConnection();
  }
}
