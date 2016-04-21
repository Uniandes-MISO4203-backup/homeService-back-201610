package co.edu.uniandes.csw.homeservices.api;

import co.edu.uniandes.csw.homeservices.entities.StatusEntity;
import java.util.List;

public interface IStatusLogic {
    public int countStatuss();
    public List<StatusEntity> getStatuss();
    public List<StatusEntity> getStatuss(Integer page, Integer maxRecords);
    public StatusEntity getStatus(Long id);
    public StatusEntity createStatus(StatusEntity entity);
    public StatusEntity updateStatus(StatusEntity entity);
    public void deleteStatus(Long id);
}
