package platform.presentation.forms;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FormForNewCode {
    private String form;

    public FormForNewCode() {
        try {
            InputStream stream = new ClassPathResource("static/new_snippet_template.html").getInputStream();
            this.form = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8)).lines()
                    .collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getForm() {
        return form;
    }
}
