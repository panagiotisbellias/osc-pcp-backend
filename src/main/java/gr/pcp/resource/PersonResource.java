package gr.pcp.resource;

import gr.pcp.caching.DefaultCacheKeyGenerator;
import gr.pcp.manager.PersonManager;
import gr.pcp.model.PersonModel;
import gr.pcp.model.dto.PeopleResponseDTO;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.Collections;
import java.util.List;

@Path("/api/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private static final Logger log = Logger.getLogger(PersonResource.class);

    private static final String CACHE_PEOPLE = "pcp-people";

    PersonManager personManager;

    @Inject
    public PersonResource(PersonManager personManager) {
        this.personManager = personManager;
    }

    @GET
//    @CacheResult(cacheName = CACHE_PEOPLE, keyGenerator = DefaultCacheKeyGenerator.class)
    public PeopleResponseDTO getAllPeople() {
        log.info("#getAllPeople");
        List<PersonModel> people = personManager.getAllPeople();
        return new PeopleResponseDTO(people);
    }

    @GET
    @Path("/{id}")
//    @CacheResult(cacheName = CACHE_PEOPLE)
    public List<PersonModel> getPersonById(@PathParam("id") @CacheKey Integer id) {
        log.info(String.format("#getPersonById(%d)", id));
        PersonModel person = personManager.getPersonById(id);
        if (person == null) {
            log.info(String.format("#getPersonById(%d) -> person = %s", id, null));
            return Collections.emptyList();
        }
        log.info(String.format("#getPersonById(%d) -> person = %s", id, person));
        return Collections.singletonList(person);
    }

    @POST
    @Transactional
    @CacheInvalidate(cacheName = CACHE_PEOPLE, keyGenerator = DefaultCacheKeyGenerator.class)
    public Response createPerson(PersonModel person) {
        log.info(String.format("#createPerson(%s)", person));
        personManager.createPerson(person);
        log.info(String.format("#createPerson(%s) -> person is created successfully", person));
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePerson(@PathParam("id") Integer id, PersonModel person) {
        log.info(String.format("#updatePerson(%d, %s)", id, person));
        PersonModel updatedPerson = personManager.updatePerson(id, person);
        if (updatedPerson == null) {
            log.info(String.format("#updatePerson(%d, %s) -> person = %s", id, person, null));
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        log.info(String.format("#updatePerson(%d, %s) -> person = %s", id, person, updatedPerson));
        invalidateCache(id);
        return Response.ok(updatedPerson).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePerson(@PathParam("id") Integer id) {
        log.info(String.format("#deletePerson(%d)", id));
        try {
            personManager.deletePerson(id);
            log.info(String.format("#updatePerson(%d) -> person with id %d is successfully deleted", id, id));
            invalidateCache(id);
            return Response.status(Response.Status.OK).build();
        } catch (NotFoundException e) {
            log.info(String.format("#updatePerson(%d) -> person with id %d doesn't exist", id, id));
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @CacheInvalidate(cacheName = CACHE_PEOPLE, keyGenerator = DefaultCacheKeyGenerator.class)
    @CacheInvalidate(cacheName = CACHE_PEOPLE)
    void invalidateCache(@CacheKey Integer id) {
        log.info(String.format("#invalidateCache(%d)", id));
    }

}
