package com.kostisProjects.MiniProject.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String title;
    private int price;
    private String brand;
    private String description;
    private String date;

    public Product() {
    }

    public Product(String id, String title, int price, String brand, String description,String date) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
