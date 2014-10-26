package domain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Cheese {

    private long id;
    private String name;
    private String description;
    private Double price;
    private Set<Address> likes;     // Lazy :)
    private List<Comment> comments; // Lazy :)
    private boolean deleted;

    public Cheese() {
    }

    public Cheese(long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // добавлен для отладки
    public Cheese(long id, String name, String description, Double price, boolean deleted) {
        this(id, name, description, price);
        this.deleted = deleted;
    }


    public long getId() {
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

    public Set<Address> getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public boolean isDeleted() {
        return deleted;
    }


    public void setId(long id) {
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

    public void setLikes(Set<Address> likes) {
        this.likes = likes;
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

    public boolean like(Address address) {
        if (likes == null) { // TODO: призадуматься
            likes = new HashSet<>();
        }
        if (!alreadyLiked(address)) {
            return likes.add(address);
        }
        return false;
    }

    public void comment(Comment comment) {
        if (comments == null) { // TODO: призадуматься
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

    private boolean alreadyLiked(Address address) {
        for (Address current : likes) {
            if (current.equals(address)) return true;
        }
        return false;
    }
}