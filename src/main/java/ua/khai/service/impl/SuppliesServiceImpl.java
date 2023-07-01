package ua.khai.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.khai.crud.CrudRepositoryHelper;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Shipments;
import ua.khai.entity.Supplies;
import ua.khai.repository.SuppliesRepository;
import ua.khai.service.SuppliesService;

import java.util.Optional;

@Service
public class SuppliesServiceImpl implements SuppliesService {

    private final SuppliesRepository suppliesRepository;
    private final CrudRepositoryHelper <Supplies, SuppliesRepository> crudRepositoryHelper;

    public SuppliesServiceImpl(SuppliesRepository suppliesRepository, CrudRepositoryHelper<Supplies, SuppliesRepository> crudRepositoryHelper) {
        this.suppliesRepository = suppliesRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Supplies entity) {
        crudRepositoryHelper.create(suppliesRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Supplies entity) {
        crudRepositoryHelper.update(suppliesRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(suppliesRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Supplies> findById(Long id) {
        return crudRepositoryHelper.findById(suppliesRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Supplies> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(suppliesRepository, request);
    }
}
