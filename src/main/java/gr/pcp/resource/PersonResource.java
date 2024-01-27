package gr.pcp.resource;

import gr.pcp.model.Person;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public Response getAllPeople() {
        List<Person> people = Person.listAll();
        return Response.ok(people).build();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Integer id) {
        Person person = Person.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

    @POST
    @Transactional
    public Response createPerson(Person person) {
        person.persist();
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePerson(@PathParam("id") Integer id, Person person) {
        Person existingPerson = Person.findById(id);
        if (existingPerson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setSalutation(person.getSalutation());
        return Response.ok(existingPerson).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePerson(@PathParam("id") Integer id) {
        Person existingPerson = Person.findById(id);
        if (existingPerson == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingPerson.delete();
        return Response.noContent().build();
    }

}
