package ua.khai.service;

import ua.khai.entity.user.Personal;

import java.util.List;

public interface PersonalCrudService extends BaseCrudService<Personal> {

    List<Personal> findAll();
}
