package FoodProductStuff;

import Database.DBConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodMenuConsole {
  private FoodProductDAO foodProductDAO;

  public FoodMenuConsole(){
    this.foodProductDAO = new FoodProductDAO(DBConnection.getConnection());
  }

  public static void main(String[] args) {
    FoodMenuConsole foodMenuConsole = new FoodMenuConsole();
    foodMenuConsole.startMenu();
  }

  public void startMenu(){
    String selected;
    boolean isLooping = true;

    while (isLooping){
      Scanner scanner = new Scanner(System.in);

      System.out.println("--------------------");
      System.out.println("The Food Store");
      System.out.println("Choose from these options (Food Product)");
      System.out.println("--------------------");
      System.out.println("[1] List all products");
      System.out.println("[2] Search for products by ID");
      System.out.println("[3] Add a new product");
      System.out.println("[4] Update a product by ID");
      System.out.println("[5] Delete a product by ID");
      System.out.println("[6] Exit");
      System.out.print("Choice :");
      selected = scanner.next();

      switch (selected){
        case "1":
          listOfAllProducts();
          break;
        case "2":
          searchFoodProductById();
          break;
        case "3":
          addNewProduct();
          break;
        case "4":
          updateProductById();
          break;
        case "5":
          deleteProductById();
          break;
        case "6":
          isLooping = false;
          break;
        default:
          System.out.println("Invalid input!");
      }
    }
  }

  private void listOfAllProducts(){
    ArrayList<FoodProduct> foodProducts = foodProductDAO.findAllProducts();

    if (foodProducts.size() == 0){
      System.out.println("Empty food!");
    }else{
      System.out.println("============================");
      for (FoodProduct foodProduct : foodProducts){
        System.out.println(foodProduct);
      }
      System.out.println("============================");
    }
  }

  private void searchFoodProductById(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Id food :");
    String selected = scanner.next();
    int id;

    try{
      id = Integer.parseInt(selected);
    }catch (Exception ex){
      System.out.println("Invalid input!");
      return;
    }

    FoodProduct foodProduct = foodProductDAO.findProduct(id);

    if (foodProduct == null){
      System.out.println("Food product not fount!");
    }else{
      System.out.println(foodProduct);
    }
  }

  public void addNewProduct(){
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter SKU :");
    String SKU = scanner.next();
    scanner = new Scanner(System.in);

    System.out.print("Enter Descrition :");
    String description = scanner.nextLine();
    scanner = new Scanner(System.in);

    System.out.print("Enter Category :");
    String category = scanner.nextLine();
    scanner = new Scanner(System.in);

    System.out.print("Enter Price :");
    int price = scanner.nextInt();

    FoodProduct foodProduct = new FoodProduct(SKU, description, category, price);
    boolean result = foodProductDAO.addProduct(foodProduct);

    if (result){
      System.out.println("Food product added successfully!");
      listOfAllProducts();
    }
  }

  public void updateProductById(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Id food :");
    String selected = scanner.next();
    int id;

    try{
      id = Integer.parseInt(selected);
    }catch (Exception ex){
      System.out.println("Invalid input!");
      return;
    }

    FoodProduct foodProduct = foodProductDAO.findProduct(id);

    if (foodProduct == null){
      System.out.println("Food product not fount!");
      return;
    }

    scanner = new Scanner(System.in);
    System.out.print("Enter new SKU (_ for skip) :");
    String answer = scanner.nextLine();
    String SKU = foodProduct.getSKU();
    if (!answer.equals("_"))
      SKU = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new description (_ for skip) :");
    answer = scanner.nextLine();
    String description = foodProduct.getDescription();
    if (!answer.equals("_"))
      description = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new category (_ for skip) :");
    answer = scanner.nextLine();
    String category = foodProduct.getCategory();
    if (!answer.equals("_"))
      category = answer;

    scanner = new Scanner(System.in);
    System.out.print("Enter new price (_ for skip) :");
    answer = scanner.nextLine();
    int price = foodProduct.getPrice();
    if (!answer.equals("_"))
      price = Integer.parseInt(answer);

    FoodProduct newFoodProduct = new FoodProduct(SKU, description, category, price);
    newFoodProduct.setId(foodProduct.getId());
    foodProductDAO.updateProduct(newFoodProduct);

    System.out.println("Food product updated successfully!");
  }

  public void deleteProductById(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Id food :");
    String answer = scanner.nextLine();
    int id;

    try{
      id = Integer.parseInt(answer);
    }catch (Exception ex){
      System.out.println("Invalid input!");
      return;
    }

    boolean result = foodProductDAO.deleteProduct(id);
    if (result)
      System.out.println("Food product successfully deleted!");
    else
      System.out.println("Food product not fount!");
  }
}
