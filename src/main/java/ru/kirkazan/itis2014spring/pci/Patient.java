package ru.kirkazan.itis2014spring.pci;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Кристина
 * Date: 27.02.14
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Patient {
    @Id
    private Integer id;
    @Column
    private String fio;
    @Column
    private Date dateOfBirth;
    @OneToMany    (mappedBy = "patient")
    private List<Address> listAddress;
    @OneToMany  (mappedBy = "patient")
    private List<Documents> listDocuments;
    @OneToMany (mappedBy = "patient")
    private List<Registration> listRegistrations;
    @Column
    private String contact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getListAddress() {
        return listAddress;
    }

    public void setListAddress(List<Address> listAddress) {
        this.listAddress = listAddress;
    }

    public List<Documents> getListDocuments() {
        return listDocuments;
    }

    public void setListDocuments(List<Documents> listDocuments) {
        this.listDocuments = listDocuments;
    }

    public List<Registration> getListRegistrations() {
        return listRegistrations;
    }

    public void setListRegistrations(List<Registration> listRegistrations) {
        this.listRegistrations = listRegistrations;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", listAddress=" + listAddress +
                ", listDocuments=" + listDocuments +
                ", listRegistrations=" + listRegistrations +
                '}';
    }
}
