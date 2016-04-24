package co.edu.uniandes.csw.homeservices.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;

/**
 * @generated
 */
@Entity
public class StatusEntity extends BaseEntity implements Serializable {
    public static final Long PUBLISHED = 1L;
    public static final Long WORKING = 2L;
    public static final Long FINISHED = 3L;
}