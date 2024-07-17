package com.gli.id.repositories;

import com.gli.id.models.OrderDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update order_details set deleted = true where order_id = ?1 and deleted = false", nativeQuery = true)
    void deleteAllByOrder_id(Integer order_id);

    @Modifying
    @Transactional
    @Query(value = "update order_details set deleted = true where id not in (?1) and order_id = ?2 and deleted = false", nativeQuery = true)
    void deleteFilteredId(List<Integer> ids, Integer order_id);
}
