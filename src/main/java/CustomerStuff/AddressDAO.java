package CustomerStuff;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class AddressDAO {
  private Connection connection;
  private String query;
  private PreparedStatement pst;
  private ResultSet rs;

  public AddressDAO(Connection connection) {
    this.connection = connection;
  }

  public ArrayList<Address> getAllAddress(){
    ArrayList<Address> addresses = new ArrayList<>();
    Address address;

    try{
      query = "SELECT * FROM address";
      pst = connection.prepareStatement(query);
      rs = pst.executeQuery();

      while (rs.next()){
        address = new Address();
        address.setAddressID(rs.getString(1));
        address.setAddressLine1(rs.getString(2));
        address.setAddressLine2(rs.getString(3));
        address.setAddressLine3(rs.getString(4));
        address.setCountry(rs.getString(5));
        address.setPostCode(rs.getString(6));

        addresses.add(address);
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return addresses;
  }

  public Address getAddressById(String addressID){
    Address address = null;

    try{
      query = "SELECT * FROM address WHERE addressID = ?";
      pst = connection.prepareStatement(query);
      pst.setString(1, addressID);
      rs = pst.executeQuery();

      if (rs.next()){
        address = new Address();
        address.setAddressID(rs.getString(1));
        address.setAddressLine1(rs.getString(2));
        address.setAddressLine2(rs.getString(3));
        address.setAddressLine3(rs.getString(4));
        address.setCountry(rs.getString(5));
        address.setPostCode(rs.getString(6));
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return address;
  }

  public String addAddress(Address address){
    String addressID = generateAddressID();

    try{
      query = "INSERT INTO address (addressID, addressLine1, addressLine2, addressLine3, country, postCode) " +
              "VALUES (?,?,?,?,?,?)";
      pst = connection.prepareStatement(query);
      pst.setString(1, addressID);
      pst.setString(2, address.getAddressLine1());
      pst.setString(3, address.getAddressLine2());
      pst.setString(4, address.getAddressLine3());
      pst.setString(5, address.getCountry());
      pst.setString(6, address.getPostCode());
      pst.executeUpdate();

    }catch (Exception ex){
      ex.printStackTrace();
      addressID = null;
    }

    return addressID;
  }

  private String generateAddressID(){
    String addressID;

    while (true){
      addressID = generateRandomWord(3);

      if (!isThereAddressById(addressID))
        break;
    }

    return addressID;
  }

  private boolean isThereAddressById(String addressID){
    boolean result = false;

    try{
      query = "SELECT * FROM address WHERE addressID = ?";
      pst = connection.prepareStatement(query);
      pst.setString(1, addressID);
      rs = pst.executeQuery();

      if (rs.next())
        result = true;
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return result;
  }

  private String generateRandomWord(int wordLength) {
    Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
    StringBuilder sb = new StringBuilder(wordLength);
    for(int i = 0; i < wordLength; i++) { // For each letter in the word
      char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
      sb.append(tmp); // Add it to the String
    }
    return sb.toString();
  }

  public boolean deleteAddressById(String addressID){
    boolean result = false;

    if (getAddressById(addressID) != null){
      result = true;
      try{
        query = "DELETE FROM address WHERE addressID = ?";
        pst = connection.prepareStatement(query);
        pst.setString(1, addressID);
        pst.execute();
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }

    return result;
  }

  public boolean updateAddress(Address address){
    boolean result = false;

    if (getAddressById(address.getAddressID()) != null){
      try{
        query = "UPDATE address SET addressLine1 = ?, addressLine2 = ?, addressLine3 = ?, country = ?, postCode = ? " +
                "WHERE addressID = ?";
        pst = connection.prepareStatement(query);
        pst.setString(1, address.getAddressLine1());
        pst.setString(2, address.getAddressLine2());
        pst.setString(3, address.getAddressLine3());
        pst.setString(4, address.getCountry());
        pst.setString(5, address.getPostCode());
        pst.setString(6, address.getAddressID());

        int rowCount = pst.executeUpdate();

        if (rowCount > 0)
          result = true;
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }

    return result;
  }

  public static void main(String[] args) {
    AddressDAO addressDAO = new AddressDAO(DBConnection.getConnection());
    testGetAllAddress(addressDAO);
  }

  public static void testIsThereAddressById(AddressDAO addressDAO){
    boolean result = addressDAO.isThereAddressById("bbb");
    System.out.println(result);
  }

  public static void testGenerateAddressID(AddressDAO addressDAO){
    for (int i = 0;i < 5;i++){
      System.out.println(addressDAO.generateAddressID());
    }
  }

  public static void testAddAddress(AddressDAO addressDAO){
    Address address = new Address();
    address.setAddressLine1("example2");
    address.setAddressLine2("example2");
    address.setAddressLine3("example2");
    address.setCountry("Indonesia");
    address.setPostCode("BL");

    String addressID = addressDAO.addAddress(address);
    boolean result = addressDAO.isThereAddressById(addressID);
    System.out.println(result);
  }

  public static void testGetAllAddress(AddressDAO addressDAO){
    ArrayList<Address> addresses = addressDAO.getAllAddress();

    for (Address address : addresses){
      System.out.println(address);
    }
  }

  public static void testDeleteAddressById(AddressDAO addressDAO){
    boolean result = addressDAO.deleteAddressById("yas");
    System.out.println(result);
  }

  public static void testUpdateAddress(AddressDAO addressDAO){
    Address address = new Address();
    address.setAddressID("lyk");
    address.setAddressLine1("example3");
    address.setAddressLine2("example3");
    address.setAddressLine3("example3");
    address.setCountry("Indonesia");
    address.setPostCode("JK");

    boolean result = addressDAO.updateAddress(address);
    System.out.println(result);
    ArrayList<Address> addresses = addressDAO.getAllAddress();

    for (Address address1 : addresses){
      System.out.println(address1);
    }
  }
}
