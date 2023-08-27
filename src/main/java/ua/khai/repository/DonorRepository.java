package ua.khai.repository;

import org.springframework.stereotype.Repository;
import ua.khai.entity.Donor;
import ua.khai.entity.user.Admin;

import java.util.List;

@Repository
public interface DonorRepository extends BaseRepository<Donor> {

}
