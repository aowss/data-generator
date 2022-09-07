import com.micasa.data.FunctionGenerator;
import com.micasa.data.Generator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Function Generator")
public class FunctionGeneratorTest {

    final Random numberGenerator = new Random();
    Generator<Integer> generator = new FunctionGenerator<>(() -> numberGenerator.nextInt());

    @Test
    @DisplayName("generates the right number of records")
    public void count() {
        var data = generator.generate(2);
        assertThat(data.length, is(2));
    }

    @Test
    @DisplayName("generates random values")
    public void values() {
        var data = generator.generate(5);
        Set<Integer> distinctValues = Set.of(data);
        //  This assertion is useless since the `Set#of` method will throw an exception in case of duplicates
        assertThat(distinctValues.size(), is(5));
    }

    @Test
    @Disabled
    @DisplayName("when ensureUniqueness is true values are unique")
    public void uniqueValues() {
        var data = generator.generate(5_000_000);
        Set<Integer> distinctValues = Set.of(data);
    }

}

