package co.edu.uniandes.csw.homeservices.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.homeservices.entities.ServiceRequestEntity;
import co.edu.uniandes.csw.crud.spi.persistence.CrudPersistence;
import co.edu.uniandes.csw.homeservices.entities.StatisticDTO;
import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 * @generated
 */
@Stateless
public class ServiceRequestPersistence extends CrudPersistence<ServiceRequestEntity> {

    private static final Logger LOGGER = Logger.getLogger(ServiceRequestPersistence.class.getCanonicalName());
    
    @PersistenceContext(unitName="HomeServicesPU")
    protected EntityManager em;

    /**
     * @generated
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @generated
     */
    @Override
    protected Class<ServiceRequestEntity> getEntityClass() {
        return ServiceRequestEntity.class;
    }
    
    public List<ServiceRequestEntity> getByDescription(String description, Long idCustomer) {
        Map<String, Object> params = new HashMap<>();
        params.put("description", "%" + description.toUpperCase() + "%");
        params.put("customerId",idCustomer);
        return executeListNamedQuery("Service.getByDescription", params);
    }
    
    
    /**
     * Metodo que nos permite obtener las estadisticas de uso 
     * de la aplcacion las cuales serán mostradas en la pagina principal
     * de la aplicacion
     * @return List<StatisticDTO> o null si no se encuentra nada
     */
    public List<StatisticDTO> getStatistics(){
        
        List<StatisticDTO> statistics = null;
        
        long statusCreated = StatusEntity.PUBLISHED;
        long statusFinished = StatusEntity.FINISHED;
        
        try {
            
            statistics = new ArrayList<>();
            
            String numClientQuery = "SELECT COUNT(cus) FROM CustomerEntity cus";
            String numConstractorsQuery = "SELECT COUNT(con) FROM ContractorEntity con";
            String numServiceReqCreateQuery = "SELECT COUNT(ser) FROM ServiceRequestEntity ser WHERE ser.status.id = :statusCreated";
            String numServiceReqFinishedQuery = "SELECT COUNT(ser) FROM ServiceRequestEntity ser WHERE ser.status.id = :statusFinished";
            String numServiceReviewQuery = "SELECT COUNT(ser) FROM ServiceRequestEntity ser JOIN ReviewEntity re WHERE ser.customer.id = re.customer.id AND ser.status.id = :statusFinished";
            
       
            Query query = em.createQuery(numClientQuery);
            long numClient = (long) query.getSingleResult();
            StatisticDTO numClientStatistic = new StatisticDTO("numClient", numClient);
            statistics.add(numClientStatistic);
            
            Query queryCon = em.createQuery(numConstractorsQuery);
            long numContractor = (long) queryCon.getSingleResult();
            StatisticDTO numContractorStatistic = new StatisticDTO("numContractor", numContractor);
            statistics.add(numContractorStatistic);
            
            Query queryReqCre= em.createQuery(numServiceReqCreateQuery);
            queryReqCre.setParameter("statusCreated", statusCreated);
            long numServiceReqCreate = (long) queryReqCre.getSingleResult();
            StatisticDTO numServiceReqCreateStatistic = new StatisticDTO("numServiceReqCreate", numServiceReqCreate);
            statistics.add(numServiceReqCreateStatistic);
            
            Query queryReqFini= em.createQuery(numServiceReqFinishedQuery);
            queryReqFini.setParameter("statusFinished", statusFinished);
            long numServiceReqFinished = (long) queryReqFini.getSingleResult();
            StatisticDTO numServiceReqFinishedStatistic = new StatisticDTO("numServiceReqFinished", numServiceReqFinished);
            statistics.add(numServiceReqFinishedStatistic);
  
            Query queryServiceReview= em.createQuery(numServiceReviewQuery);
            queryServiceReview.setParameter("statusFinished", statusFinished);
            long numServiceReview = (long) queryServiceReview.getSingleResult();
            StatisticDTO numServiceReviewStatistic = new StatisticDTO("numServiceReview", numServiceReview);
            statistics.add(numServiceReviewStatistic);
            
        } catch (Exception e) {
            statistics = null;
            LOGGER.severe("A ocurrido un error al consultar las estadisticas de la pagina ".concat(e.getMessage()));
        }
        
        return statistics;
        
    }
}
