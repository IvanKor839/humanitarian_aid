package ua.khai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Product extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "count")
    private Integer count;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "size")
    private Integer size;

    @Column(name = "description")
    private String description;

    public Product() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
