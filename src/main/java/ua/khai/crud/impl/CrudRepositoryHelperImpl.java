package ua.khai.crud.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.khai.crud.CrudRepositoryHelper;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.BaseEntity;
import ua.khai.exception.EntityNotFoundException;
import ua.khai.repository.BaseRepository;

import java.util.Optional;

@Service
public class CrudRepositoryHelperImpl<
        E extends BaseEntity,
        R extends BaseRepository<E>>
        implements CrudRepositoryHelper<E, R> {

    @Override
    public void create(R repository, E entity) {
        repository.save(entity);
    }

    @Override
    public void update(R repository, E entity) {
        checkExist(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(R repository, Long id) {
        checkExist(repository, id);
        return repository.findById(id);
    }

    @Override
    public DataTableResponse<E> findAll(R repository, DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<E> entityPage = repository.findAll(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<E> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        return dataTableResponse;
    }

    private void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("this entity is not found");
        }
    }
}
