package lt.vu.persistence;

import lt.vu.entities.Students;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
public class StudentsDAO {

    @Inject
    private EntityManager em;

    public List<Students> loadAll(Integer classGroupId) {
        return em.createNamedQuery("Students.findAll", Students.class).setParameter("classId", classGroupId).getResultList();
    }

    public void persist(Students students) { this.em.persist(students); }

    public Students findOne(Integer id) { return em.find(Students.class, id); }

    public Students update(Students students) { return em.merge(students); }
}
