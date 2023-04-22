package com.bookstoreversion2.data.rating;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="ratings")
public class Rating {

    @EmbeddedId
    private RatingItemPK ratingItemPK;
    @Column(name="rating")
    private double rating;

    public Rating(RatingItemPK ratingItemPK, double rating) {
        this.ratingItemPK = ratingItemPK;
        this.rating = rating;
    }

    public Rating() {

    }
}
