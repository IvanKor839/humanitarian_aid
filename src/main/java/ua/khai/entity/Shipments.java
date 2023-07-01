package ua.khai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import ua.khai.entity.user.Admin;
import ua.khai.entity.user.Personal;

@Entity
public class Shipments extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Personal personal;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "count")
    private Integer count;

    public Shipments() {
        super();
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
