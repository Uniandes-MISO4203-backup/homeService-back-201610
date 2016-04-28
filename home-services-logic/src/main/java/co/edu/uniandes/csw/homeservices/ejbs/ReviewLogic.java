package co.edu.uniandes.csw.homeservices.ejbs;


import co.edu.uniandes.csw.homeservices.api.IReviewLogic;
import co.edu.uniandes.csw.homeservices.entities.ReviewEntity;
import co.edu.uniandes.csw.homeservices.persistence.ReviewPersistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ReviewLogic implements IReviewLogic {

    @Inject private ReviewPersistence persistence;

    /**
     * @return 
     * @generated
     */
    @Override
    public int countReviews() {
        return persistence.count();
    }

    /**
     * @return 
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews() {
        return persistence.findAll();
    }

    /**
     * @param page
     * @param maxRecords
     * @return 
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @param id
     * @return 
     * @generated
     */
    @Override
    public ReviewEntity getReview(Long id) {
        return persistence.find(id);
    }

    /**
     * @param entity
     * @return 
     * @generated
     */
    @Override
    public ReviewEntity createReview(ReviewEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @param entity
     * @return 
     * @generated
     */
    @Override
    public ReviewEntity updateReview(ReviewEntity entity) {
        ReviewEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @param id
     * @generated
     */
    @Override
    public void deleteReview(Long id) {
        persistence.delete(id);
    }
}
