package ua.khai.service;

import ua.khai.entity.user.Admin;
import ua.khai.entity.user.Personal;

import java.util.List;

public interface AdminService extends BaseCrudService<Admin> {

    List<Admin> findAll();
}
