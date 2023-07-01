package ua.khai.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import ua.khai.type.RoleType;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin() {
        super();
        setRoleType(RoleType.ROLE_ADMIN);
    }
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
