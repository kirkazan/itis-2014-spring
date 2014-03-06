package ru.kirkazan.itis2014spring.pci;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Кристина
 * Date: 27.02.14
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class TypeDocuments {
    @Id
    private Integer id;
    @Column
    private String name;

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

    @Override
    public String toString() {
        return "TypeDocuments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
