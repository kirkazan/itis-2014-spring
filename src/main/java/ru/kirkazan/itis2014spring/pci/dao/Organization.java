package ru.kirkazan.itis2014spring.pci.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Кристина
 * Date: 27.02.14
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Organization {
    @Id
    private Integer id;
    @Column
    private String name;

    @ManyToOne
    private TypeOrganization type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOrganization getType() {
        return type;
    }

    public void setType(TypeOrganization type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
