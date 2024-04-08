package CustomerStuff;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAO {
  private Connection connection;
  private String query;
  private PreparedStatement pst;
  private ResultSet rs;
  private AddressDAO addressDAO;

  public CustomerDAO(Connection connection) {
    this.connection = connection;
    this.addressDAO = new AddressDAO(connection);
  }

  public ArrayList<Customer> findAllCustomer(){
    ArrayList<Customer> customers = new ArrayList<>();
    Customer customer;

    try{
      query = "SELECT * FROM customer";
      pst = connection.prepareStatement(query);
      rs = pst.executeQuery();

      while (rs.next()){
        customer = new Customer();
        customer.setCustomerID(rs.getInt(1));
        customer.setBusinessName(rs.getString(2));
        Address address = addressDAO.getAddressById(rs.getString(3));
        customer.setAddress(address);
        customer.setTelephoneNumber(rs.getString(4));

        customers.add(customer);
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return customers;
  }

  public Customer findCustomerById(int id){
    Customer customer = null;

    try{
      query = "SELECT * FROM customer WHERE customerID = ?";
      pst = connection.prepareStatement(query);
      pst.setInt(1, id);
      rs = pst.executeQuery();

      if (rs.next()){
        customer = new Customer();
        customer.setCustomerID(rs.getInt(1));
        customer.setBusinessName(rs.getString(2));
        Address address = addressDAO.getAddressById(rs.getString(3));
        customer.setAddress(address);
        customer.setTelephoneNumber(rs.getString(4));
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return customer;
  }

  public boolean deleteCustomerById(int id){
    boolean result = false;
    Customer customer = findCustomerById(id);

    if (customer != null){
      String addressID = customer.getAddress().getAddressID();

      try{
        query = "DELETE FROM customer WHERE customerID = ?";
        pst = connection.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();

        addressDAO.deleteAddressById(addressID);
        result = true;
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }

    return result;
  }

  public boolean addCustomer(Customer customer){
    boolean result = false;
    String addressID = addressDAO.addAddress(customer.getAddress());

    if (addressID != null){
      try{
        query = "INSERT INTO customer (businessName, addressID, telephoneNumber) " +
                "VALUES (?,?,?)";
        pst = connection.prepareStatement(query);
        pst.setString(1, customer.getBusinessName());
        pst.setString(2, addressID);
        pst.setString(3, customer.getTelephoneNumber());
        pst.executeUpdate();

        result = true;
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }

    return result;
  }

  public boolean updateCustomer(Customer customer){
    boolean result = false;
    Address address = customer.getAddress();

    if (findCustomerById(customer.getCustomerID()) != null){
      boolean resultUpdateAddress = addressDAO.updateAddress(address);

      if (resultUpdateAddress){
        try{
          query = "UPDATE customer SET businessName = ?, addressID = ?, telephoneNumber = ? " +
                  "WHERE customerID = ?";
          pst = connection.prepareStatement(query);
          pst.setString(1, customer.getBusinessName());
          pst.setString(2, customer.getAddress().getAddressID());
          pst.setString(3, customer.getTelephoneNumber());
          pst.setInt(4, customer.getCustomerID());

          int rowCount = pst.executeUpdate();
          if (rowCount > 0)
            result = true;
        }catch (Exception ex){
          ex.printStackTrace();
        }
      }
    }

    return result;
  }

  public static void main(String[] args) {
    CustomerDAO customerDAO = new CustomerDAO(DBConnection.getConnection());
    testFindAllCustomer(customerDAO);
  }

  public static void testFindAllCustomer(CustomerDAO customerDAO){
    ArrayList<Customer> customers = customerDAO.findAllCustomer();

    for (Customer customer : customers){
      System.out.println(customer);
    }
  }

  public static void testAddCustomer(CustomerDAO customerDAO){
    Address address = new Address();
    address.setAddressLine1("example4");
    address.setAddressLine2("example4");
    address.setAddressLine3("example4");
    address.setCountry("Indonesia");
    address.setPostCode("JK");

    Customer customer = new Customer();
    customer.setBusinessName("Akbar");
    customer.setAddress(address);
    customer.setTelephoneNumber("(425) 555-1214");

    boolean result = customerDAO.addCustomer(customer);
    System.out.println(result);
  }

  public static void testFindCustomerById(CustomerDAO customerDAO){
    Customer customer = customerDAO.findCustomerById(3);
    System.out.println(customer);
  }

  public static void testDeleteCustomerById(CustomerDAO customerDAO){
    boolean result = customerDAO.deleteCustomerById(1);
    System.out.println(result);
    ArrayList<Customer> customers = customerDAO.findAllCustomer();

    for (Customer customer : customers){
      System.out.println(customer);
    }
  }

  public static void testUpdateCustomer(CustomerDAO customerDAO){
    Customer customer = customerDAO.findCustomerById(2);
    Address address = customer.getAddress();

    customer.setBusinessName("John");
    address.setCountry("United Kingdom");
    boolean result = customerDAO.updateCustomer(customer);
    System.out.println(result);

    ArrayList<Customer> customers = customerDAO.findAllCustomer();

    for (Customer customer1 : customers){
      System.out.println(customer1);
    }
  }
}
