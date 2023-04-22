package com.bookstoreversion2.data.repo;

import com.bookstoreversion2.data.rating.Rating;
import com.bookstoreversion2.data.rating.RatingItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, RatingItemPK> {
}
