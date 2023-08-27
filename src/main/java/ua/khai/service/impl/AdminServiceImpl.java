package ua.khai.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.khai.crud.CrudRepositoryHelper;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.user.Admin;
import ua.khai.entity.user.Personal;
import ua.khai.exception.EntityNotFoundException;
import ua.khai.repository.AdminRepository;
import ua.khai.service.AdminService;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AdminRepository adminRepository;
    private final CrudRepositoryHelper <Admin, AdminRepository> crudRepositoryHelper;

    public AdminServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, AdminRepository adminRepository, CrudRepositoryHelper<Admin, AdminRepository> crudRepositoryHelper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.adminRepository = adminRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Admin entity) {
        System.out.println(adminRepository.existsByEmail(entity.getEmail()));
        if(adminRepository.existsByEmail(entity.getEmail())){
            throw new EntityNotFoundException("This person is exist");
        }
        System.out.println(entity.getPassword());
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        crudRepositoryHelper.create(adminRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Admin entity) {
        crudRepositoryHelper.update(adminRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(adminRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Admin> findById(Long id) {
        return crudRepositoryHelper.findById(adminRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Admin> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(adminRepository, request);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
}
