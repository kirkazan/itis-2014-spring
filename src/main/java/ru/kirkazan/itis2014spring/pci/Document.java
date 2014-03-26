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
public class Document
{
    @Id
    private Integer id;
    @Column
    private String series;
    @Column
    private String number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Document{" +
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
