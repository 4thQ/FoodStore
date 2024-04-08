package CustomerStuff;

public class Customer {
  private int customerID;
  private String businessName;
  private Address address;
  private String telephoneNumber;

  public Customer(String businessName, Address address, String telephoneNumber) {
    this.businessName = businessName;
    this.address = address;
    this.telephoneNumber = telephoneNumber;
  }

  public Customer() {
  }

  public int getCustomerID() {
    return customerID;
  }

  public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "customerID=" + customerID +
            ", businessName='" + businessName + '\'' +
            ", address=" + address +
            ", telephoneNumber='" + telephoneNumber + '\'' +
            '}';
  }
}
