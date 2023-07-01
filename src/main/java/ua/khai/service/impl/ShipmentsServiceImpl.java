package ua.khai.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.khai.crud.CrudRepositoryHelper;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Donor;
import ua.khai.entity.Shipments;
import ua.khai.repository.ShipmentsRepository;
import ua.khai.service.ShipmentsService;

import java.util.Optional;

@Service
public class ShipmentsServiceImpl implements ShipmentsService {

    private final ShipmentsRepository shipmentsRepository;
    private final CrudRepositoryHelper <Shipments, ShipmentsRepository> crudRepositoryHelper;

    public ShipmentsServiceImpl(ShipmentsRepository shipmentsRepository, CrudRepositoryHelper<Shipments, ShipmentsRepository> crudRepositoryHelper) {
        this.shipmentsRepository = shipmentsRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Shipments entity) {
        crudRepositoryHelper.create(shipmentsRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Shipments entity) {
        crudRepositoryHelper.update(shipmentsRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(shipmentsRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Shipments> findById(Long id) {
        return crudRepositoryHelper.findById(shipmentsRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Shipments> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(shipmentsRepository, request);
    }
}
