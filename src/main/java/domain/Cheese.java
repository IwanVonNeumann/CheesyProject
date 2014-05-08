package domain;

import java.util.LinkedList;
import java.util.List;

public class Cheese {

    private int id;
    private String name;
    private String description;
    private Double price;
    private List<Comment> comments; // Lazy :)
    private boolean deleted;

    public Cheese() {
    }

    public Cheese(int id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // добавлен для отладки
    public Cheese(int id, String name, String description, Double price, boolean deleted) {
        this(id, name, description, price);
        this.deleted = deleted;
    }


    public int getId() {
        return id;
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

    public List<Comment> getComments() {
        return comments;
    }

    public boolean isDeleted() {
        return deleted;
    }


    public void setId(int id) {
        this.id = id;
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

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }



    public void delete() {
        setDeleted(true);
    }

    public void comment(Comment comment) {
        if (comments == null) {
            comments = new LinkedList<>();
        }
        comments.add(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cheese)) return false;

        Cheese cheese = (Cheese) o;

        return name.equals(cheese.name);
    }

    @Override
    public String toString() {
        return "Cheese{" + name + '\t' + description + '\t' + price + '}';
    }
}