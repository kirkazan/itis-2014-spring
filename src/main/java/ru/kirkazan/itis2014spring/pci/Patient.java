package ru.kirkazan.itis2014spring.pci;

import javax.persistence.*;
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
    @Id @GeneratedValue()
    private Integer id;
    @Column
    private String surname;
    @Column
    private String name;
    @Column
    private String father;
    @Column
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @OneToMany    (mappedBy = "patient")
    private List<Address> listAddress;
    @OneToMany  (mappedBy = "patient")
    private List<Document> listDocuments;
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

    public List<Document> getListDocuments() {
        return listDocuments;
    }

    public void setListDocuments(List<Document> listDocuments) {
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
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", father='" + father + '\'' +
                ", dateOfBirth=" + dateOfBirth +

                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}
