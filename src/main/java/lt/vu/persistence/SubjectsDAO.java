package lt.vu.persistence;


import lt.vu.entities.Subjects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SubjectsDAO {

    @Inject
    private EntityManager em;

    public List<Subjects> loadAll() {
        return em.createNamedQuery("subjects.loadAll", Subjects.class).getResultList();
    }
    public void setEM(EntityManager em) { this.em = em; }

    public void persist(Subjects subjects) {
        this.em.persist(subjects);
    }

    public Subjects findOne(Integer id) {
        return em.find(Subjects.class, id);
    }

}
