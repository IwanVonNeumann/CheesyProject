package domain;

public class Cheese {

    private String name;
    private String description;
    private Double price;
    private int id;

    public Cheese(String name, String description,
                  Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Cheese(String name, String description, Double price, int id) {
        this(name, description, price);
        this.id = id;
    }

    public Cheese() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cheese{" + name + '\t' + description + '\t' + price + '}';
    }
}
