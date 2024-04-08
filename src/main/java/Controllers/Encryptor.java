package Controllers;



import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
  public String encryptorString(String input) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] messageDigest = md.digest(input.getBytes());
    BigInteger bigInteger = new BigInteger(1, messageDigest);
    return bigInteger.toString(16);
  }

  public static void main(String[] args) throws NoSuchAlgorithmException{
    Encryptor encryptor = new Encryptor();
    String password = "Admin2";
    String hashPassword = "21eed4f2e9ab214fdbf00a2a091d63c4";

    System.out.println(encryptor.encryptorString(password));

//    if (encryptor.encryptorString(password).equals(hashPassword)){
//      System.out.println("Correct");
//    }else {
//      System.out.println("Wrong");
//    }
  }
}
