package lt.vu.persistence;

import lt.vu.entities.Teacher;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
public class TeacherDAO {

    @Inject
    private EntityManager em;

    public List<Teacher> loadAll(Integer classId){
        return em.createNamedQuery("teacher.findAll", Teacher.class).setParameter("classId", classId).getResultList();
    }
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public void persist(Teacher teacher){
        this.em.persist(teacher);
    }

    public Teacher findOne(Integer id) {
        return em.find(Teacher.class, id, LockModeType.OPTIMISTIC);
    }

}
