import com.micasa.data.CSVLookupGenerator;
import com.micasa.data.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CSV Lookup Generator")
public class CSVLookupGeneratorTest {

    Generator<String> generator = new CSVLookupGenerator("src/test/resources/country.csv", "Code");

    @Test
    @DisplayName("generates the right number of records")
    public void count() {
        var data = generator.generate(2);
        assertThat(data.length, is(2));
    }

    @Test
    @DisplayName("picks values sequentially from the file")
    public void values() {
        var data = generator.generate(3);
        assertThat(data[0], is("AF"));
        assertThat(data[1], is("AX"));
        assertThat(data[2], is("AL"));
    }

    @Test
    @DisplayName("throws an exception if the file doesn't exist")
    public void fileNotFound() {
        Generator<String> generator = new CSVLookupGenerator("src/test/resources/countries.csv", "Code");
        Exception exception = assertThrows(RuntimeException.class, () -> generator.generate(3));
        assertThat(exception.getMessage(), is("Unable to load the file at src/test/resources/countries.csv"));
    }

}

