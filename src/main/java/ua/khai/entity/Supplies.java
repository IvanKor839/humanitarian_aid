package ua.khai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import ua.khai.entity.user.Personal;

@Entity
public class Supplies extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Donor donor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "count")
    private Integer count;

    public Supplies() {
        super();
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
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
