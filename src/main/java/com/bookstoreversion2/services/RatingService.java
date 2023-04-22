package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.rating.Rating;
import com.bookstoreversion2.data.entities.User;
import com.bookstoreversion2.data.rating.RatingItemPK;
import com.bookstoreversion2.data.repo.BookRepository;
import com.bookstoreversion2.data.repo.RatingRepository;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserServiceImp userServiceImp;

    public void add(User user, Book book, double rating) {
        ratingRepository.save(new Rating(new RatingItemPK(user, book), rating));
    }

    public List<Book> getRecommendation() {
        List<Book> recommendedBooks = new ArrayList<>();
        List<User> users = userServiceImp.getUsers();
        try {

            List<Rating> ratings = ratingRepository.findAll();

            HashMap<Long, PreferenceArray> hashMap = new HashMap<>();
            List<GenericPreference> genericPreferenceList = new ArrayList<>();
            for (Rating rating : ratings) {
                genericPreferenceList.add(new GenericPreference(rating.getRatingItemPK().getUser().getId(),
                        rating.getRatingItemPK().getBook().getId(), (float) rating.getRating()));
            }
            for (User value : users) {
                List<GenericPreference> userPreference = new ArrayList<>();
                for (GenericPreference preference : genericPreferenceList) {
                    if (value.getId() == preference.getUserID()) {
                        userPreference.add(preference);
                    }
                }
                if (userPreference.size() > 0) {
                    PreferenceArray preferenceArray = new GenericUserPreferenceArray(userPreference);
                    hashMap.put(value.getId(), preferenceArray);
                }
            }
            FastByIDMap<PreferenceArray> userData = new FastByIDMap<>(3);
            Set<Long> keys = hashMap.keySet();
            for (Long key : keys) {
                userData.put(key, hashMap.get(key));
            }
            DataModel realModel = new GenericDataModel(userData);

            UserSimilarity similarity = new PearsonCorrelationSimilarity(realModel);

            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, realModel);
            UserBasedRecommender recommender = new GenericUserBasedRecommender(realModel, neighborhood, similarity);
            List<RecommendedItem> recommendedItems;
            recommendedItems = recommender.recommend(30, 2);


            for (RecommendedItem item : recommendedItems) {
                Book book = bookRepository.findById(item.getItemID()).get();
                recommendedBooks.add(book);
            }
        } catch (TasteException e) {
            throw new RuntimeException(e);
        }
        return recommendedBooks;
    }
}
