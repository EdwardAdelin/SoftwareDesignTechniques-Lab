package lab22;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonalDataAdapterTest {

    @Test
    public void testAdapterConvertsDataToJSON() {
        PersonalDataI person = new PersonalData(
                "Catherine Smith",
                LocalDate.of(1965, 2, 19),
                "smithcath@mail.com",
                "0721142312"
        );

        PersonalInformation adapter = new PersonalDataAdapter(person);
        String json = adapter.getPersonalInformation();

        assertTrue(json.contains("\"name\": \"Catherine Smith\""));
        assertTrue(json.contains("\"yearOfBirth\": 1965"));
        assertTrue(json.contains("\"email\": \"smithcath@mail.com\""));
        assertTrue(json.contains("\"telephone\": \"0721142312\""));
    }
}
