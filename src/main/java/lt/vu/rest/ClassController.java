package lt.vu.rest;


import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Classes;
import lt.vu.persistence.ClassesDAO;
import lt.vu.rest.contracts.ClassesDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/classes")
public class ClassController {

    @Inject
    @Getter @Setter
    private ClassesDAO classesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Classes classes = classesDAO.findOne(id);
        if (classes == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ClassesDto classesDto = new ClassesDto();
        classesDto.setName(classes.getName());

        return Response.ok(classesDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer classId, ClassesDto classesDto) {
        try {
            Classes existingClass = classesDAO.findOne(classId);
            if (existingClass == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingClass.setName(classesDto.getName());

            classesDAO.update(existingClass);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ClassesDto classesDto) {
        try {
            Classes newClass = new Classes();
            newClass.setName(classesDto.getName());

            classesDAO.persist(newClass);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
