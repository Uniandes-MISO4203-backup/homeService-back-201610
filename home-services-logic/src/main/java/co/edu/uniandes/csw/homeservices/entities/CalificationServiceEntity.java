package co.edu.uniandes.csw.homeservices.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author l.badillo10
 */
@Entity
public class CalificationServiceEntity extends BaseEntity implements Serializable{
    private String description;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    

}


