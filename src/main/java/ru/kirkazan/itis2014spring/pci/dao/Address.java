package ru.kirkazan.itis2014spring.pci.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Кристина
 * Date: 27.02.14
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Address {
    @Id
    private Integer id;
    @Column
    private String address;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private TypeAddress type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TypeAddress getType() {
        return type;
    }

    public void setType(TypeAddress type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", patient=" + patient +
                ", type=" + type +
                '}';
    }
}
