import com.micasa.data.CSVLookupGenerator;
import com.micasa.data.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CSV Lookup Generator")
class CSVLookupGeneratorTest {

    Generator<String> generator = new CSVLookupGenerator("src/test/resources/country.csv", "Code");

    @Test
    @DisplayName("generates the right number of records")
    void count() {
        var data = generator.generate(2);
        assertThat(data.length, is(2));
    }

    @Test
    @DisplayName("picks values sequentially from the file")
    void values() {
        var data = generator.generate(3);
        assertThat(data[0], is("AF"));
        assertThat(data[1], is("AX"));
        assertThat(data[2], is("AL"));
    }

    @Test
    @DisplayName("picks values from the start again when the end is reached")
    void loop() {
        Generator<String> generator = new CSVLookupGenerator("src/test/resources/country-small.csv", "Code");
        var data = generator.generate(3);
        assertThat(data[0], is("AF"));
        assertThat(data[1], is("AX"));
        assertThat(data[2], is("AF"));
    }

    @Test
    @DisplayName("throws an exception if the header doesn't exist")
    void headerNotFound() {
        Generator<String> generator = new CSVLookupGenerator("src/test/resources/country.csv", "Country Code");
        Exception exception = assertThrows(RuntimeException.class, () -> generator.generate(3));
        assertThat(exception.getMessage(), is("The file at src/test/resources/country.csv doesn't contain the header 'Country Code'"));
    }

    @Test
    @DisplayName("throws an exception if the file doesn't have a header")
    void noHeader() {
        Generator<String> generator = new CSVLookupGenerator("src/test/resources/country-no-header.csv", "Code");
        Exception exception = assertThrows(RuntimeException.class, () -> generator.generate(3));
        assertThat(exception.getMessage(), is("The file at src/test/resources/country-no-header.csv doesn't contain the header 'Code'"));
    }

    @Test
    @DisplayName("throws an exception if the file is empty")
    void emptyFile() {
        Generator<String> generator = new CSVLookupGenerator("src/test/resources/country-empty.csv", "Code");
        Exception exception = assertThrows(RuntimeException.class, () -> generator.generate(3));
        assertThat(exception.getMessage(), is("The file at src/test/resources/country-empty.csv is empty"));
    }

    @Test
    @DisplayName("throws an exception if the file doesn't exist")
    void fileNotFound() {
        Generator<String> generator = new CSVLookupGenerator("src/test/resources/countries.csv", "Code");
        Exception exception = assertThrows(RuntimeException.class, () -> generator.generate(3));
        assertThat(exception.getMessage(), is("Unable to load the file at src/test/resources/countries.csv"));
    }

}

