package co.edu.uniandes.csw.homeservices.api;


import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import java.util.List;

public interface IReviewLogic {
    public int countReviews();
    public List<ReviewEntity> getReviews();
    public List<ReviewEntity> getReviews(Integer page, Integer maxRecords);
    public ReviewEntity getReview(Long id);
    public ReviewEntity createReview(ReviewEntity entity);
    public ReviewEntity updateReview(ReviewEntity entity);
    public void deleteReview(Long id);
}
