package domain;

public class Cheese {

    private int id;
    private String name;
    private String description;
    private Double price;
    private boolean deleted;

    public Cheese() {
    }

    public Cheese(String name, String description, Double price, int id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
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

    public boolean isDeleted() {
        return deleted;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }



    public void delete() {
        setDeleted(true);
    }

    @Override
    public String toString() {
        return "Cheese{" + name + '\t' + description + '\t' + price + '}';
    }
}
