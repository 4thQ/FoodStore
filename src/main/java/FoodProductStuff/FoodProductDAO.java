package FoodProductStuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Database.DBConnection;

public class FoodProductDAO {
  private Connection connection;
  private String query;
  private PreparedStatement pst;
  private ResultSet rs;

  public FoodProductDAO(Connection connection) {
    this.connection = connection;
  }

  public ArrayList<FoodProduct> findAllProducts(){
    ArrayList<FoodProduct> foodProducts = new ArrayList<>();
    FoodProduct foodProduct;

    try{
      query = "SELECT * FROM foodproduct";
      pst = connection.prepareStatement(query);
      rs = pst.executeQuery();

      while (rs.next()){
        foodProduct = new FoodProduct();
        foodProduct.setId(rs.getInt(1));
        foodProduct.setSKU(rs.getString(2));
        foodProduct.setDescription(rs.getString(3));
        foodProduct.setCategory(rs.getString(4));
        foodProduct.setPrice(rs.getInt(5));
        foodProducts.add(foodProduct);
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return foodProducts;
  }

  public FoodProduct findProduct(int id){
    FoodProduct foodProduct = null;

    try{
      query = "SELECT * FROM foodproduct WHERE id = ?";
      pst = connection.prepareStatement(query);
      pst.setInt(1, id);
      rs = pst.executeQuery();

      if (rs.next()){
        foodProduct = new FoodProduct();
        foodProduct.setId(rs.getInt(1));
        foodProduct.setSKU(rs.getString(2));
        foodProduct.setDescription(rs.getString(3));
        foodProduct.setCategory(rs.getString(4));
        foodProduct.setPrice(rs.getInt(5));
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return foodProduct;
  }

  public boolean deleteProduct(int id){
    boolean result = false;

    if (isThereFoodProductById(id)){
      result = true;

      try{
        query = "DELETE FROM foodproduct WHERE id = ?";
        pst = connection.prepareStatement(query);
        pst.setInt(1, id);
        pst.execute();
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }

    return result;
  }

  private boolean isThereFoodProductById(int id){
    boolean result = false;

    try{
      query = "SELECT * FROM foodproduct WHERE id = ?";
      pst = connection.prepareStatement(query);
      pst.setInt(1, id);
      rs = pst.executeQuery();

      if (rs.next())
        result = true;
    }catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }

  public boolean updateProduct(FoodProduct foodProduct){
    boolean result = false;

    if (isThereFoodProductById(foodProduct.getId())){
      try{
        query = "UPDATE foodproduct SET SKU = ?, description = ?, category = ?, price = ? WHERE id = ?";
        pst = connection.prepareStatement(query);
        pst.setString(1, foodProduct.getSKU());
        pst.setString(2, foodProduct.getDescription());
        pst.setString(3, foodProduct.getCategory());
        pst.setInt(4, foodProduct.getPrice());
        pst.setInt(5, foodProduct.getId());

        int rowCount = pst.executeUpdate();

        if (rowCount > 0)
          result = true;
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }

    return result;
  }

  public boolean addProduct(FoodProduct foodProduct){
    boolean result = false;

    try{
      query = "INSERT INTO foodproduct (SKU, description, category, price) " +
              "VALUES (?,?,?,?)";
      pst = connection.prepareStatement(query);
      pst.setString(1, foodProduct.getSKU());
      pst.setString(2, foodProduct.getDescription());
      pst.setString(3, foodProduct.getCategory());
      pst.setInt(4, foodProduct.getPrice());
      pst.executeUpdate();

      result = true;
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return result;
  }

  public ArrayList<FoodProduct> getFoodProductByCategory(String category){
    ArrayList<FoodProduct> foodProducts = new ArrayList<>();
    FoodProduct foodProduct;

    try{
      query = "SELECT * FROM foodproduct WHERE category = ?";
      pst = connection.prepareStatement(query);
      pst.setString(1, category);
      rs = pst.executeQuery();

      while (rs.next()){
        foodProduct = new FoodProduct();
        foodProduct.setId(rs.getInt(1));
        foodProduct.setSKU(rs.getString(2));
        foodProduct.setDescription(rs.getString(3));
        foodProduct.setCategory(rs.getString(4));
        foodProduct.setPrice(rs.getInt(5));
        foodProducts.add(foodProduct);
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return foodProducts;
  }

  public ArrayList<String> getCateforyOfFoodProduct(){
    ArrayList<String> categoryFoodProduct = new ArrayList<>();
    String category;

    try{
      query = "SELECT DISTINCT category FROM foodproduct";
      pst = connection.prepareStatement(query);
      rs = pst.executeQuery();

      while (rs.next()){
        category = rs.getString(1);
        categoryFoodProduct.add(category);
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return categoryFoodProduct;
  }

  public ArrayList<FoodProduct> getFoodProductByDescription(String keyWord){
    ArrayList<FoodProduct> foodProducts = new ArrayList<>();
    FoodProduct foodProduct = null;

    try{
      query = "SELECT * FROM foodproduct WHERE description LIKE ?";
      pst = connection.prepareStatement(query);
      String word = "%" + keyWord + "%";
      pst.setString(1, word);
      rs = pst.executeQuery();

      while (rs.next()){
        foodProduct = new FoodProduct();
        foodProduct.setId(rs.getInt(1));
        foodProduct.setSKU(rs.getString(2));
        foodProduct.setDescription(rs.getString(3));
        foodProduct.setCategory(rs.getString(4));
        foodProduct.setPrice(rs.getInt(5));
        foodProducts.add(foodProduct);
      }
    }catch (Exception ex){
      ex.printStackTrace();
    }
    return foodProducts;
  }

  public static void main(String[] args) {
    FoodProductDAO foodProductDAO = new FoodProductDAO(DBConnection.getConnection());
    testGetFoodProductByDescription(foodProductDAO);
  }

  public static void testFindAllProducts(FoodProductDAO foodProductDAO){
    ArrayList<FoodProduct> foodProducts = foodProductDAO.findAllProducts();

    for (int i = 0;i < foodProducts.size();i++){
      System.out.println(foodProducts.get(i));
    }
  }

  public static void testFindProduct(FoodProductDAO foodProductDAO){
    FoodProduct foodProduct = foodProductDAO.findProduct(2);
    System.out.println(foodProduct);
  }

  public static void testDeleteProduct(FoodProductDAO foodProductDAO){
    boolean result = foodProductDAO.deleteProduct(1);
    System.out.println(result);
  }

  public static void testIsThereFoodProductById(FoodProductDAO foodProductDAO){
    boolean result = foodProductDAO.isThereFoodProductById(1);
    System.out.println(result);
  }

  public static void testUpdateProduct(FoodProductDAO foodProductDAO){
    FoodProduct foodProduct = new FoodProduct();
    foodProduct.setId(2);
    foodProduct.setSKU("BN135");
    foodProduct.setDescription("Box of bananas");
    foodProduct.setCategory("Fruit");
    foodProduct.setPrice(40);

    boolean result = foodProductDAO.updateProduct(foodProduct);

    System.out.println(result);
    testFindAllProducts(foodProductDAO);
  }

  public static void testAddProduct(FoodProductDAO foodProductDAO){
    FoodProduct foodProduct = new FoodProduct();
    foodProduct.setSKU("PN44");
    foodProduct.setDescription("5Kg of Penne Pasta");
    foodProduct.setCategory("Pasta");
    foodProduct.setPrice(30);

    boolean result = foodProductDAO.addProduct(foodProduct);
    System.out.println(result);
    testFindAllProducts(foodProductDAO);
  }

  public static void testGetFoodProductByCategory(FoodProductDAO foodProductDAO){
    ArrayList<FoodProduct> foodProducts = foodProductDAO.getFoodProductByCategory("Pasta");

    for (FoodProduct foodProduct : foodProducts){
      System.out.println(foodProduct);
    }
  }

  public static void testGetCateforyOfFoodProduct(FoodProductDAO foodProductDAO){
    ArrayList<String> categories = foodProductDAO.getCateforyOfFoodProduct();

    for (String category : categories){
      System.out.println(category);
    }
  }

  public static void testGetFoodProductByDescription(FoodProductDAO foodProductDAO){
    ArrayList<FoodProduct> foodProducts = foodProductDAO.getFoodProductByDescription("5");

    for (FoodProduct foodProduct : foodProducts){
      System.out.println(foodProduct);
    }
  }
}
