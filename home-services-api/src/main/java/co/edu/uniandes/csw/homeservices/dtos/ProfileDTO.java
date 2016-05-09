package co.edu.uniandes.csw.homeservices.dtos;

import com.stormpath.sdk.account.Account;

public class ProfileDTO {

    private String name;
    private String lastName;
    private String document;
    private String picture;

    public ProfileDTO(Account acc) {
        this.name = acc.getGivenName();
        this.lastName = acc.getSurname();
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @generated
     */
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    /**
     * @generated
     */
    public String getDocument() {
        return document;
    }

    /**
     * @generated
     */
    public void setDocument(String document) {
        this.document = document;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

}
