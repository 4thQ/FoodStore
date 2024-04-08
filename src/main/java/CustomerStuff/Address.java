package CustomerStuff;

public class Address {
  private String addressID;
  private String addressLine1;
  private String addressLine2;
  private String addressLine3;
  private String country;
  private String postCode;

  public Address() {
  }

  public Address(String addressLine1, String addressLine2, String addressLine3, String country, String postCode) {
    this.addressLine1 = addressLine1;
    this.addressLine2 = addressLine2;
    this.addressLine3 = addressLine3;
    this.country = country;
    this.postCode = postCode;
  }

  public String getAddressID() {
    return addressID;
  }

  public void setAddressID(String addressID) {
    this.addressID = addressID;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getAddressLine3() {
    return addressLine3;
  }

  public void setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  @Override
  public String toString() {
    return "Address{" +
            "addressID='" + addressID + '\'' +
            ", addressLine1='" + addressLine1 + '\'' +
            ", addressLine2='" + addressLine2 + '\'' +
            ", addressLine3='" + addressLine3 + '\'' +
            ", country='" + country + '\'' +
            ", postCode='" + postCode + '\'' +
            '}';
  }
}
