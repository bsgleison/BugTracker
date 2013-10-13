package br.tcc.webapp.model;

import org.appfuse.model.BaseObject;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Gleison
 * Date: 27/07/13
 * Time: 27:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@org.hibernate.search.annotations.Indexed
public class Status extends BaseObject {

    private Long id;

    @org.hibernate.search.annotations.Field(index = Index.YES)
    private String description;

// --------------------- GETTER / SETTER METHODS ---------------------

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.hibernate.search.annotations.DocumentId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @org.hibernate.search.annotations.Field(index = Index.YES)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (!id.equals(status.id)) return false;
        if (!description.equals(status.description)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
