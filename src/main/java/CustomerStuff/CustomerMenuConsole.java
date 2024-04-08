package CustomerStuff;

import Database.DBConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenuConsole {
  private CustomerDAO customerDAO;

  public CustomerMenuConsole() {
    this.customerDAO = new CustomerDAO(DBConnection.getConnection());
  }

  public static void main(String[] args) {
    CustomerMenuConsole customerMenuConsole = new CustomerMenuConsole();
    customerMenuConsole.startMenu();
  }

  public void startMenu(){
    String selected;
    boolean isLooping = true;

    while (isLooping){
      Scanner scanner = new Scanner(System.in);

      System.out.println("--------------------");
      System.out.println("The Food Store");
      System.out.println("Choose from these options (Customer)");
      System.out.println("--------------------");
      System.out.println("[1] List all customer");
      System.out.println("[2] Search for customer by ID");
      System.out.println("[3] Add a new customer");
      System.out.println("[4] Update a customer by ID");
      System.out.println("[5] Delete a customer by ID");
      System.out.println("[6] Exit");
      System.out.print("Choice :");
      selected = scanner.next();

      switch (selected){
        case "1":
          listOfAllCustomer();
          break;
        case "2":
          searchCustomerById();
          break;
        case "3":
          addCustomer();
          break;
        case "4":
          updateCustomerById();
          break;
        case "5":
          deleteCustomerById();
          break;
        case "6":
          isLooping = false;
          break;
        default:
          System.out.println("Invalid input!");
      }
    }
  }

  private void listOfAllCustomer(){
    ArrayList<Customer> customers = customerDAO.findAllCustomer();

    if (customers.isEmpty()){
      System.out.println("Empty food!");
    }else {
      System.out.println("============================");
      for (Customer customer : customers){
        System.out.println(customer);
      }
      System.out.println("============================");
    }
  }

  private void searchCustomerById(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Customer ID :");
    String selected = scanner.next();
    int id;

    try{
      id = Integer.parseInt(selected);
    }catch (Exception ex){
      System.out.println("Invalid input!");
      return;
    }

    Customer customer = customerDAO.findCustomerById(id);

    if (customer == null){
      System.out.println("Customer not found!");
    }else{
      System.out.println(customer);
    }
  }

  private void addCustomer(){
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter Business Name :");
    String businessName = scanner.next();
    scanner = new Scanner(System.in);

    System.out.print("Enter Address Line 1 :");
    String addressLine1 = scanner.next();
    scanner = new Scanner(System.in);

    System.out.print("Enter Address Line 2 :");
    String addressLine2 = scanner.next();
    scanner = new Scanner(System.in);

    System.out.print("Enter Address Line 3 :");
    String addressLine3 = scanner.next();
    scanner = new Scanner(System.in);

    System.out.print("Enter Country :");
    String country = scanner.next();
    scanner = new Scanner(System.in);

    System.out.print("Enter Post Code :");
    String postCode = scanner.next();
    scanner = new Scanner(System.in);

    System.out.print("Enter Telephone Number :");
    String telephoneNumber = scanner.next();
    scanner = new Scanner(System.in);

    Address address = new Address(addressLine1, addressLine2, addressLine3, country, postCode);
    Customer customer = new Customer(businessName, address, telephoneNumber);
    boolean result = customerDAO.addCustomer(customer);

    if (result){
      System.out.println("Customer added successfully!");
      listOfAllCustomer();
    }else{
      System.out.println("Customer failed to enter!");
    }
  }

  private void deleteCustomerById(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter customer ID :");
    String answer = scanner.nextLine();
    int id;

    try{
      id = Integer.parseInt(answer);
    }catch (Exception ex){
      System.out.println("Invalid input!");
      return;
    }

    boolean result = customerDAO.deleteCustomerById(id);
    if (result)
      System.out.println("Customer successfully deleted!");
    else
      System.out.println("Customer not fount!");
  }

  private void updateCustomerById(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Id Customer :");
    String selected = scanner.next();
    int id;

    try{
      id = Integer.parseInt(selected);
    }catch (Exception ex){
      System.out.println("Invalid input!");
      return;
    }

    Customer customer = customerDAO.findCustomerById(id);

    if (customer == null){
      System.out.println("Customer not fount!");
      return;
    }

    System.out.println(customer);

    scanner = new Scanner(System.in);
    System.out.print("Enter new Business Name (_ for skip) :");
    String answer = scanner.nextLine();
    String businessName = customer.getBusinessName();
    if (!answer.equals("_"))
      businessName = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new Address Line 1 (_ for skip) :");
    answer = scanner.nextLine();
    String addressLine1 = customer.getAddress().getAddressLine1();
    if (!answer.equals("_"))
      addressLine1 = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new Address Line 2 (_ for skip) :");
    answer = scanner.nextLine();
    String addressLine2 = customer.getAddress().getAddressLine2();
    if (!answer.equals("_"))
      addressLine2 = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new Address Line 3 (_ for skip) :");
    answer = scanner.nextLine();
    String addressLine3 = customer.getAddress().getAddressLine3();
    if (!answer.equals("_"))
      addressLine3 = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new Country (_ for skip) :");
    answer = scanner.nextLine();
    String country = customer.getAddress().getCountry();
    if (!answer.equals("_"))
      country = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new Post Code (_ for skip) :");
    answer = scanner.nextLine();
    String postCode = customer.getAddress().getPostCode();
    if (!answer.equals("_"))
      postCode = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new Telephone Number (_ for skip) :");
    answer = scanner.nextLine();
    String telephoneNumber = customer.getTelephoneNumber();
    if (!answer.equals("_"))
      telephoneNumber = answer;

    Address updateAddress = new Address(addressLine1, addressLine2, addressLine3, country, postCode);
    updateAddress.setAddressID(customer.getAddress().getAddressID());
    Customer updateCustomer = new Customer(businessName, updateAddress, telephoneNumber);
    updateCustomer.setCustomerID(customer.getCustomerID());

    System.out.println(updateCustomer);

    boolean result = customerDAO.updateCustomer(updateCustomer);

    if (result){
      System.out.println("Customer updated successfully!");
      listOfAllCustomer();
    }else{
      System.out.println("Customer failed to update!");
    }
  }
}
