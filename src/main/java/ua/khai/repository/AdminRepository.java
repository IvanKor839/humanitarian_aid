package ua.khai.repository;

import org.springframework.stereotype.Repository;
import ua.khai.entity.user.Admin;
import ua.khai.entity.user.Personal;

@Repository
public interface AdminRepository extends UserRepository<Admin> {

    Admin findByEmail(String email);
}
