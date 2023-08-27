package ua.khai.service;


import ua.khai.entity.Donor;

import java.util.List;

public interface DonorService extends BaseCrudService<Donor> {
    List<Donor> findAll();
}
