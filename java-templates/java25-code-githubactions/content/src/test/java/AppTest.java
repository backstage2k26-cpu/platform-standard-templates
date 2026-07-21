import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void createsApplicationInstance() {
        assertThat(new App()).isNotNull();
    }

    @Test
    void printsApplicationGreeting() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            App.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        assertThat(output.toString(StandardCharsets.UTF_8)).isEqualTo("Hello from market" + System.lineSeparator());
    }
}
