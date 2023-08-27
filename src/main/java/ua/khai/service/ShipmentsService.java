package ua.khai.service;

import ua.khai.datatable.DataTableRequest;
import ua.khai.datatable.DataTableResponse;
import ua.khai.entity.Shipments;
import ua.khai.entity.Supplies;

public interface ShipmentsService extends BaseCrudService<Shipments> {

    DataTableResponse<Shipments> findAllByPerson(DataTableRequest request, Long id);
}
