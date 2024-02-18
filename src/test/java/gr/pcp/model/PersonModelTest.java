package gr.pcp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonModelTest {

    @Test
    void testCreation() {
        PersonModel person1 = new PersonModel(1, "test", "test", "test");
        PersonModel person2 = new PersonModel();

        Assertions.assertNotNull(person1);
        Assertions.assertInstanceOf(PersonModel.class, person1);
        Assertions.assertNotNull(person2);
        Assertions.assertInstanceOf(PersonModel.class, person2);
    }

    @Test
    void testGettersSetters() {
        PersonModel person = new PersonModel();
        person.setId(1);
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setSalutation("salutation");

        Assertions.assertEquals(1, person.getId());
        Assertions.assertEquals("firstName", person.getFirstName());
        Assertions.assertEquals("lastName", person.getLastName());
        Assertions.assertEquals("salutation", person.getSalutation());
    }

    @Test
    void testToString() {
        PersonModel person = new PersonModel(1, "test", "test", "test");
        Assertions.assertEquals("Person{" +
                "id='1'" +
                ", firstName='test'" +
                ", lastName='test'" +
                ", salutation='test'" +
                "}",
                person.toString());
    }

}
