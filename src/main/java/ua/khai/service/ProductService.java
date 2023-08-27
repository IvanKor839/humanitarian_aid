package ua.khai.service;


import ua.khai.entity.Product;

import java.util.List;

public interface ProductService extends BaseCrudService<Product> {

    List<Product> findAll();
}
