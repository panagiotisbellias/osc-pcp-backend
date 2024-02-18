package gr.pcp.manager;

import gr.pcp.model.PersonModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class PersonManager {

    @PersistenceContext
    EntityManager entityManager;

    public List<PersonModel> getAllPeople() {
        return entityManager.createQuery("SELECT p FROM PersonModel p", PersonModel.class).getResultList();
    }

    public PersonModel getPersonById(Integer id) {
        return entityManager.find(PersonModel.class, id);
    }

    public void createPerson(PersonModel person) {
        entityManager.persist(person);
    }

    public PersonModel updatePerson(Integer id, PersonModel person) {
        PersonModel existingPerson = getPersonById(id);
        if (existingPerson == null) {
            return null;
        }
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setSalutation(person.getSalutation());
        return existingPerson;
    }

    public void deletePerson(Integer id) {
        PersonModel existingPerson = getPersonById(id);
        if (existingPerson == null) {
            throw new NotFoundException("Person with id " + id + " doesn't exist");
        }
        entityManager.remove(existingPerson);
    }

}
