package ua.khai.repository;

import org.springframework.stereotype.Repository;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Donor;
import ua.khai.entity.Shipments;

import java.util.List;

@Repository
public interface ShipmentsRepository extends BaseRepository<Shipments> {

    DataTableResponse<Shipments> findAllByPersonalId(Long personal_id);
}
