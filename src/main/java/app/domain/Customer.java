package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//POJO - Plain Old Java Object
public class Customer {
    private final Long id;
    private boolean isActive;
    private String name;
    private final List<Product> product = new ArrayList<>();

    public Customer(Long id, boolean isActive, String name) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return isActive == customer.isActive && Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(product, customer.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, name, product);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", product=" + product +
                '}';
    }

}
