package ua.khai.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.khai.crud.CrudRepositoryHelper;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Donor;
import ua.khai.entity.Product;
import ua.khai.repository.DonorRepository;
import ua.khai.service.DonorService;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;
    private final CrudRepositoryHelper <Donor, DonorRepository> crudRepositoryHelper;

    public DonorServiceImpl(DonorRepository donorRepository, CrudRepositoryHelper<Donor, DonorRepository> crudRepositoryHelper) {
        this.donorRepository = donorRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Donor entity) {
        crudRepositoryHelper.create(donorRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Donor entity) {
        crudRepositoryHelper.update(donorRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(donorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Donor> findById(Long id) {
        return crudRepositoryHelper.findById(donorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Donor> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(donorRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Donor> findAll() {
        return donorRepository.findAll();
    }
}
