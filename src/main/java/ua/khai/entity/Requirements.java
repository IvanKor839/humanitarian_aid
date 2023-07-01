package ua.khai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import ua.khai.entity.user.Personal;

@Entity
public class Requirements extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Personal personal;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "count")
    private Integer count;

    @Column(name = "status")
    private String  status;

    public Requirements() {
        super();
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
