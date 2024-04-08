package Controllers;

import Database.DBConnection;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
  private Connection connection;
  private String query;
  private PreparedStatement pst;
  private ResultSet rs;

  public UserDAO(Connection connection) {
    this.connection = connection;
  }

  public User getUserByUsernameAndPassword(String username, String password)throws NoSuchAlgorithmException {
    User user = null;
    Encryptor encryptor = new Encryptor();
    String hashPassword = encryptor.encryptorString(password);

    try{
      query = "SELECT * FROM user WHERE username = ? AND password = ?";
      pst = connection.prepareStatement(query);
      pst.setString(1, username);
      pst.setString(2, hashPassword);
      rs = pst.executeQuery();

      if (rs.next()){
        user = new User();
        user.setUserID(rs.getInt(1));
        user.setUsername(rs.getString(2));
        user.setPassword(rs.getString(3));
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }
    return user;
  }

  public static void main(String[] args)throws NoSuchAlgorithmException {
    UserDAO userDAO = new UserDAO(DBConnection.getConnection());
    testGetUserByUsernameAndPassword(userDAO);
  }

  public static void testGetUserByUsernameAndPassword(UserDAO userDAO)throws NoSuchAlgorithmException{
    User user = userDAO.getUserByUsernameAndPassword("admin2", "Admin2");
    System.out.println(user);
  }
}
