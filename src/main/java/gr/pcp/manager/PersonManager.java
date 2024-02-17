package gr.pcp.manager;

import gr.pcp.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class PersonManager {

    public List<Person> getAllPeople() {
        return Person.listAll();
    }

    public Person getPersonById(Integer id) {
        return Person.findById(id);
    }

    public void createPerson(Person person) {
        person.persist();
    }

    public Person updatePerson(Integer id, Person person) {
        Person existingPerson = getPersonById(id);
        if (existingPerson == null) {
            return null;
        }
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setSalutation(person.getSalutation());
        return existingPerson;
    }

    public void deletePerson(Integer id) {
        Person existingPerson = getPersonById(id);
        if (existingPerson == null) {
            throw new NotFoundException("Person with id " + id + " doesn't exist");
        }
        existingPerson.delete();
    }

}
