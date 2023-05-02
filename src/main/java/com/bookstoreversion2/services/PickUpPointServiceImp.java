package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.PickUpPoint;
import com.bookstoreversion2.data.repo.PickUpPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickUpPointServiceImp implements PickUpPointService {
    @Autowired
    private PickUpPointRepository pickUpPointRepository;

    @Override
    public boolean save(PickUpPoint pickUpPoint) {
        boolean flag = false;
        if (pickUpPointRepository.findPickUpPointByAddress(pickUpPoint.getAddress()) == null) {
            pickUpPointRepository.save(pickUpPoint);
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean update(PickUpPoint pickUpPoint) {
        boolean flag = false;
        PickUpPoint pickUpPointDB = pickUpPointRepository.findPickUpPointById(pickUpPoint.getId());
        if (pickUpPointDB != null) {
            pickUpPointDB.setAddress(pickUpPoint.getAddress());
            pickUpPointDB.setWorkSchedule(pickUpPoint.getWorkSchedule());
            pickUpPointDB.setStartOfBusiness(pickUpPoint.getStartOfBusiness());
            pickUpPointDB.setCloseOfBusiness(pickUpPoint.getCloseOfBusiness());
            pickUpPointRepository.save(pickUpPointDB);
            flag = true;
        }
        return flag;
    }

    @Override
    public void delete(PickUpPoint pickUpPoint) {
        pickUpPointRepository.delete(pickUpPoint);
    }

    @Override
    public PickUpPoint findByAddress(String address) {
        return pickUpPointRepository.findPickUpPointByAddress(address);
    }

    @Override
    public PickUpPoint findById(Long id) {
        return pickUpPointRepository.findPickUpPointById(id);
    }

    @Override
    public List<PickUpPoint> findAll() {
        return pickUpPointRepository.findAll();
    }

    @Override
    public void ratePickUpPoint(Long id, double rating) {
        PickUpPoint pickUpPointDB = pickUpPointRepository.findPickUpPointById(id);
        if (pickUpPointDB.getRating() == 0) {
            pickUpPointDB.setRating(rating);
        } else {
            pickUpPointDB.setRating((pickUpPointDB.getRating() + rating) / 2);
        }
        pickUpPointRepository.save(pickUpPointDB);
    }
}
