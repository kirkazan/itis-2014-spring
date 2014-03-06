package ru.kirkazan.itis2014spring.pci;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Кристина
 * Date: 27.02.14
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Documents {
    @Id
    private Integer id;
    @Column
    private Integer series;
    @Column
    private Integer number;
    @Column
    private Date dateFrom;
    @Column
    private Date dateTo;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    private TypeDocuments type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
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

    public TypeDocuments getType() {
        return type;
    }

    public void setType(TypeDocuments type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", series=" + series +
                ", number=" + number +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", patient=" + patient +
                ", organization=" + organization +
                '}';
    }
}
