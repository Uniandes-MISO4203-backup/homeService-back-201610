package co.edu.uniandes.csw.homeservices.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan camilo cerquera <jc.cerquera10@uniandes.edu.co>
 */
@XmlRootElement
public class EducationDTO {
    private Long id;
    private String name;
    private String institute;
    private String year;
    private ContractorDTO contractor;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ContractorDTO getContractor() {
        return contractor;
    }

    public void setContractor(ContractorDTO contractor) {
        this.contractor = contractor;
    }
}
