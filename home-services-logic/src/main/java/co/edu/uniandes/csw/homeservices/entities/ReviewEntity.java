package co.edu.uniandes.csw.homeservices.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ReviewEntity extends BaseEntity implements Serializable {

    private String source;
    
    private String description;
    
    private int value;
    
    /**
     * Relación Muchos a uno con CustomerEntity
     * Esta relación es mapeada desde CustomerEntity por la relación en el atributo
     * reviews.
     * La anotación crea una llave foránea en la base de datos que
     * apunta a la tabla de ContractorEntity
     */
    @PodamExclude
    @ManyToOne
    private CustomerEntity customer;
    
    /**
     * Relación Muchos a uno con ContractorEntity
     * Esta relación es mapeada desde ContractorEntity por la relación en el atributo
     * reviews.
     * La anotación crea una llave foránea en la base de datos que
     * apunta a la tabla de ContractorEntity
     */
    @PodamExclude
    @ManyToOne
    private ContractorEntity contractor;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the contractor
     */
    public ContractorEntity getContractor() {
        return contractor;
    }

    /**
     * @param contractor the contractor to set
     */
    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }
}
