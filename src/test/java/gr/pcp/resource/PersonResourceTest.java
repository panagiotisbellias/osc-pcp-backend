package gr.pcp.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PersonResourceTest {

    @InjectMocks
    PersonResource personResource;

    @Test
    void testGetAllPeople() {

    }

    @Test
    void testGetPersonById() {

    }

    @Test
    void testCreatePerson() {

    }

    @Test
    void testUpdatePerson() {

    }

    @Test
    void testDeletePerson() {

    }

    @Test
    void testInvalidateCache() {

    }

}
