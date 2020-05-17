package lt.vu.persistence;


import lt.vu.entities.Classes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
public class ClassesDAO {

    @Inject
    private EntityManager em;

    public List<Classes> loadAll(){
        return em.createNamedQuery("classes.loadAll", Classes.class).getResultList();
    }

    public void persist(Classes classes){
        this.em.persist(classes);
    }

    public Classes findOne(Integer id){
        return em.find(Classes.class, id);
    }

    public Classes update(Classes classes) { return em.merge(classes); }
}
