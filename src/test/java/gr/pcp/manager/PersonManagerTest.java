package gr.pcp.manager;

import gr.pcp.model.PersonModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonManagerTest {

    @Mock
    EntityManager entityManager;

    @Mock
    TypedQuery<PersonModel> typedQuery;

    @InjectMocks
    PersonManager personManager;

    PersonModel person1;
    PersonModel person2;

    @BeforeEach
    public void setup() {
        person1 = Mockito.mock(PersonModel.class);
        person2 = Mockito.mock(PersonModel.class);
        Mockito.when(entityManager.find(PersonModel.class, 1)).thenReturn(person1);
    }

    @Test
    void testGetAllPeople() {
        Mockito.when(entityManager.createQuery("SELECT p FROM PersonModel p", PersonModel.class)).thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(List.of(person1, person2));

        List<PersonModel> people = personManager.getAllPeople();
        Assertions.assertEquals(List.of(person1, person2), people);
    }

    @Test
    void testGetPersonById() {
        PersonModel person2 = personManager.getPersonById(1);
        Assertions.assertEquals(person1, person2);
    }

    @Test
    void testCreatePerson() {
        personManager.createPerson(person1);
        Mockito.verify(entityManager).persist(person1);
    }

    @Test
    void testUpdatePerson() {
        PersonModel person2 = personManager.updatePerson(1, person1);
        Assertions.assertEquals(person1, person2);
    }

    @Test
    void testDeletePerson() {
        personManager.deletePerson(1);
        Mockito.verify(entityManager).remove(person1);
    }

}
