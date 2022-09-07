import com.micasa.data.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Enum Generator")
class EnumGeneratorTest {

    Generator<String> generator = new EnumGenerator<>(new String[] { "male", "female" });

    @Test
    @DisplayName("generates the right number of records")
    void count() {
        var data = generator.generate(2);
        assertThat(data.length, is(2));
    }


    @Test
    @DisplayName("picks values evenly from the list")
    void values() {
        var data = generator.generate(5);
        assertThat(data[0], is("male"));
        assertThat(data[2], is("male"));
        assertThat(data[4], is("male"));
        assertThat(data[1], is("female"));
        assertThat(data[3], is("female"));
    }

}

