package gr.pcp.resource;

import gr.pcp.manager.PersonManager;
import gr.pcp.model.PersonModel;
import gr.pcp.model.dto.PeopleResponseDTO;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonResourceTest {

    @Mock
    PersonManager personManager;

    @InjectMocks
    PersonResource personResource;

    @Test
    void testGetAllPeople() {
        Mockito.when(personManager.getAllPeople()).thenReturn(List.of());
        PeopleResponseDTO people = personResource.getAllPeople();
        Assertions.assertEquals(List.of(), people.getData());
        Assertions.assertEquals(0, people.getTotal());
    }

    @Test
    void testGetPersonById() {
        PersonModel person = Mockito.mock(PersonModel.class);
        Mockito.when(personManager.getPersonById(1)).thenReturn(person);

        List<PersonModel> people = personResource.getPersonById(1);
        Assertions.assertEquals(Collections.singletonList(person), people);
    }

    @Test
    void testCreatePerson() {
        PersonModel person = Mockito.mock(PersonModel.class);
        try (Response response = personResource.createPerson(person)) {
            Assertions.assertEquals(201, response.getStatus());
        }
    }

    @Test
    void testUpdatePerson() {
        PersonModel person = Mockito.mock(PersonModel.class);
        Mockito.when(personManager.updatePerson(1, person)).thenReturn(person);

        try (Response response = personResource.updatePerson(1, person)) {
            Assertions.assertEquals(200, response.getStatus());
        }
    }

    @Test
    void testDeletePerson() {
        try (Response response = personResource.deletePerson(1)) {
            Assertions.assertEquals(200, response.getStatus());
        }
    }

}
