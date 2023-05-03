package com.bookstoreversion2.services;

import com.bookstoreversion2.data.entities.Book;
import com.bookstoreversion2.data.entities.User;
import com.bookstoreversion2.data.rating.Rating;
import com.bookstoreversion2.data.rating.RatingItemPK;
import com.bookstoreversion2.data.repo.BookRepository;
import com.bookstoreversion2.data.repo.RatingRepository;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.model.PlusAnonymousConcurrentUserDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookRepository bookRepository;

    public void add(User user, Book book, double rating) {
        Rating ratingDB = ratingRepository.findRatingByRatingItemPK(new RatingItemPK(user, book));
        if (ratingDB == null) {
            ratingRepository.save(new Rating(new RatingItemPK(user, book), rating));
        } else {
            ratingDB.setRating((ratingDB.getRating() + rating) / 2);
            ratingRepository.save(ratingDB);
        }
    }

    public List<Book> getRecommendation() {
        List<Book> recommendedBooks = new ArrayList<>();
        try {
            DataModel realModel = getUserData();
            PlusAnonymousConcurrentUserDataModel plusModel = new PlusAnonymousConcurrentUserDataModel(realModel, 4);
            Long anonymousUserId = plusModel.takeAvailableUser();
            PreferenceArray tempPrefs = new GenericUserPreferenceArray(2);
            tempPrefs.setUserID(0, anonymousUserId);
            tempPrefs.setItemID(0, 11);
            tempPrefs.setValue(0, (float) 2.3);
            tempPrefs.setUserID(1, anonymousUserId);
            tempPrefs.setItemID(1, 16);
            tempPrefs.setValue(1, 5);
            plusModel.setTempPrefs(tempPrefs, anonymousUserId);

            UserSimilarity similarity = new PearsonCorrelationSimilarity(plusModel);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, plusModel);
            Recommender recommender = new GenericUserBasedRecommender(plusModel, neighborhood, similarity);
            List<RecommendedItem> recommendedItems;
            recommendedItems = recommender.recommend(anonymousUserId, 2);
            plusModel.releaseUser(anonymousUserId);
            for (RecommendedItem item : recommendedItems) {
                Book book = bookRepository.findById(item.getItemID()).get();
                recommendedBooks.add(book);
            }
        } catch (TasteException e) {
            throw new RuntimeException(e);
        }
        return recommendedBooks;
    }

    public DataModel getUserData() {
        DataModel dataModel;
        try {
            dataModel = new FileDataModel(writeRatingDataFromDBToFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataModel;
    }

    private File writeRatingDataFromDBToFile() {
        File file = new File("D:/book-store-version-2/src/main/java/com/bookstoreversion2/data/rating/rating.txt");
        List<Rating> ratings = ratingRepository.findAll();
        try {
            Writer writer = new FileWriter(file, false);
            ratings.forEach(rating -> {
                try {
                    writer.write(rating.getRatingItemPK().getUser().getId() + "," + rating.getRatingItemPK().getBook().getId() + "," + rating.getRating() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
