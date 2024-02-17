package gr.pcp.resource;

import gr.pcp.manager.PersonManager;
import gr.pcp.model.Person;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    PersonManager personManager;

    @Inject
    public PersonResource(PersonManager personManager) {
        this.personManager = personManager;
    }

    @GET
    public Response getAllPeople() {
        return Response.ok(personManager.getAllPeople()).build();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Integer id) {
        Person person = personManager.getPersonById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

    @POST
    @Transactional
    public Response createPerson(Person person) {
        personManager.createPerson(person);
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePerson(@PathParam("id") Integer id, Person person) {
        Person updatedPerson = personManager.updatePerson(id, person);
        if (updatedPerson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedPerson).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePerson(@PathParam("id") Integer id) {
        try {
            personManager.deletePerson(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
