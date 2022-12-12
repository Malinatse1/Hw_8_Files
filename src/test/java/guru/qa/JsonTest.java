package guru.qa;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;



public class JsonTest  {
    ClassLoader cl = JsonTest.class.getClassLoader();

    @Test
    void jsonParseTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("test.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            assertThat(jsonObject.get("email").getAsString()).isEqualTo("test@test.com");
            assertThat(jsonObject.get("password").getAsString()).isEqualTo("12345");
            assertThat(jsonObject.get("company").getAsString()).isEqualTo("DNS");
            assertThat(jsonObject.get("firstname").getAsString()).isEqualTo("Test");
            assertThat(jsonObject.get("lastname").getAsString()).isEqualTo("User");
            assertThat(jsonObject.get("phone").getAsString()).isEqualTo("322-223");
            assertThat(jsonObject.get("level").getAsInt()).isEqualTo(20);
            assertThat(jsonObject.get("lang").getAsString()).isEqualTo("English");
            assertThat(jsonObject.get("active").getAsInt()).isEqualTo(0);
            assertThat(jsonObject.get("department").getAsString()).isEqualTo("Head");
            assertThat(jsonObject.get("emp_id").getAsString()).isEqualTo("123456");
        }
    }

}
