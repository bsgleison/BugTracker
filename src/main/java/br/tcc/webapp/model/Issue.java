package br.tcc.webapp.model;

import org.apache.lucene.document.Field;
import org.appfuse.model.BaseObject;
import org.appfuse.model.User;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: luiza
 * Date: 28/07/13
 * Time: 15:19
 */

@Entity
@Indexed
public class Issue extends BaseObject {
// ------------------------------ FIELDS ------------------------------

    private Long id;
    @org.hibernate.search.annotations.Field(index = Index.YES)
    private String summary;
    private String description;
    private Departament departament;
    private Activity activity = new Activity();
    private Status status =  new Status();
    private User reporter = new User();
    private User assigned = new User();
    private Project project = new Project();
    private Date expectedDate;
    private List<History> history;


// --------------------- GETTER / SETTER METHODS ---------------------


    public Issue() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @OneToOne
    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }
    @org.hibernate.search.annotations.Field(index = Index.YES)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    @OneToOne
    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    @OneToOne
    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    @OneToOne
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToOne
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    @org.hibernate.search.annotations.Field(index = Index.YES)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (!id.equals(issue.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", departament=" + departament +
                ", activity=" + activity +
                ", status=" + status +
                ", reporter=" + reporter +
                ", assigned=" + assigned +
                ", status=" + status +
                ", project=" + project +
                ", expectedDate=" + expectedDate +
                ", history=" + history +
                '}';
    }
}
