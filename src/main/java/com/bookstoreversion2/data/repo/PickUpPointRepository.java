package com.bookstoreversion2.data.repo;

import com.bookstoreversion2.data.entities.PickUpPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickUpPointRepository extends JpaRepository<PickUpPoint, Long> {
    PickUpPoint findPickUpPointById(Long id);
    PickUpPoint findPickUpPointByAddress(String address);

}
