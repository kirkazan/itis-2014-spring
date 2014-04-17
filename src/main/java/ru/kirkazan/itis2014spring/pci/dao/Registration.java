package ru.kirkazan.itis2014spring.pci.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Кристина
 * Date: 27.02.14
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */          
@Entity
public class Registration {
    @Id
    private Integer id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Organization organization;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", patient=" + patient +
                ", organization=" + organization +
                '}';
    }
}
