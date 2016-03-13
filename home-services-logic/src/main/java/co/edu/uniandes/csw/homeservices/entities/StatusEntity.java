package co.edu.uniandes.csw.homeservices.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;

/**
 * @generated
 */
@Entity
public class StatusEntity extends BaseEntity implements Serializable {
    //Id of finished status in database
    public static final Long FINISHED = 2L;
}