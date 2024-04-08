package FoodProductStuff;

public class FoodProduct {
  private int id;
  private String SKU;
  private String description;
  private String category;
  private int price;

  public FoodProduct(String SKU, String description, String category, int price) {
    this.SKU = SKU;
    this.description = description;
    this.category = category;
    this.price = price;
  }

  public FoodProduct() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSKU() {
    return SKU;
  }

  public void setSKU(String SKU) {
    this.SKU = SKU;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "FoodProduct{" +
            "id=" + id +
            ", SKU='" + SKU + '\'' +
            ", description='" + description + '\'' +
            ", category='" + category + '\'' +
            ", price=" + price +
            '}';
  }
}
