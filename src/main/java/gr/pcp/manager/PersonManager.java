package gr.pcp.manager;

import gr.pcp.model.PersonModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class PersonManager {

    public List<PersonModel> getAllPeople() {
        return PersonModel.listAll();
    }

    public PersonModel getPersonById(Integer id) {
        return PersonModel.findById(id);
    }

    public void createPerson(PersonModel person) {
        person.persist();
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
        existingPerson.delete();
    }

}
