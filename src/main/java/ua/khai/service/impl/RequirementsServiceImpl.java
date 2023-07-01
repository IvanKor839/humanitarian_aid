package ua.khai.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.khai.crud.CrudRepositoryHelper;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Donor;
import ua.khai.entity.Requirements;
import ua.khai.repository.RequirementsRepository;
import ua.khai.service.RequirementsService;

import java.util.Optional;

@Service
public class RequirementsServiceImpl implements RequirementsService {

    private final RequirementsRepository requirementsRepository;
    private final CrudRepositoryHelper <Requirements, RequirementsRepository> crudRepositoryHelper;

    public RequirementsServiceImpl(RequirementsRepository requirementsRepository, CrudRepositoryHelper<Requirements, RequirementsRepository> crudRepositoryHelper) {
        this.requirementsRepository = requirementsRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Requirements entity) {
        crudRepositoryHelper.create(requirementsRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Requirements entity) {
        crudRepositoryHelper.update(requirementsRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(requirementsRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Requirements> findById(Long id) {
        return crudRepositoryHelper.findById(requirementsRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Requirements> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(requirementsRepository, request);
    }
}
