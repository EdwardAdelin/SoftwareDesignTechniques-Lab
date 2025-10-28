package lab22;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class PersonalDataAdapterTest {

    @Test
    public void testAdapterConvertsDataToJSON() {
        PersonalDataI person = new PersonalData(
                "Alice Johnson",
                LocalDate.of(1995, 4, 12),
                "alice@example.com",
                "0712345678"
        );

        PersonalInformation adapter = new PersonalDataAdapter(person);
        String json = adapter.getPersonalInformation();

        assertTrue(json.contains("\"name\": \"Alice Johnson\""));
        assertTrue(json.contains("\"yearOfBirth\": 1995"));
        assertTrue(json.contains("\"email\": \"alice@example.com\""));
        assertTrue(json.contains("\"telephone\": \"0712345678\""));
    }
}
