package gr.pcp.manager;

import gr.pcp.model.PersonModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

@Disabled("Ignored due to new untested functionality. Tests must be refactored")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonManagerTest {

    @InjectMocks
    PersonManager personManager;

    @Test
    void testGetAllPeople() {
        PersonModel person1 = Mockito.mock(PersonModel.class);
        PersonModel person2 = Mockito.mock(PersonModel.class);
        Mockito.when(PersonModel.listAll()).thenReturn(List.of(person1, person2));

        List<PersonModel> people = personManager.getAllPeople();
        Assertions.assertEquals(List.of(person1, person2), people);
    }

    @Test
    void testGetPersonById() {

    }

    @Test
    void testCreatePerson() {
        PersonModel person = Mockito.mock(PersonModel.class);
        personManager.createPerson(person);
        Mockito.verify(person).persist();
    }

    @Test
    void testUpdatePerson() {
        PersonModel person = Mockito.mock(PersonModel.class);
        PersonModel existingPerson = personManager.updatePerson(1, person);
        Assertions.assertEquals(person, existingPerson);
    }

    @Test
    void testDeletePerson() {

    }

}
