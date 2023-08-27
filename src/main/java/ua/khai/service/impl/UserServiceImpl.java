package ua.khai.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.khai.crud.CrudRepositoryHelper;
import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Supplies;
import ua.khai.entity.user.User;
import ua.khai.repository.UserRepository;
import ua.khai.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CrudRepositoryHelper <User, UserRepository<User>> crudRepositoryHelper;

    public UserServiceImpl(UserRepository userRepository, CrudRepositoryHelper<User, UserRepository<User>> crudRepositoryHelper) {
        this.userRepository = userRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(User entity) {
        crudRepositoryHelper.create(userRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(User entity) {
        crudRepositoryHelper.update(userRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(userRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return crudRepositoryHelper.findById(userRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(userRepository, request);
    }
}
