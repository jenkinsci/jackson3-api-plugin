package tools.jackson.databind.toml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.jvnet.hudson.test.junit.jupiter.RealJenkinsExtension;
import tools.jackson.dataformat.toml.TomlMapper;

/**
 * Basic tests for {@link TomlMapper}.
 */
class TOMLMapperTest {

    @RegisterExtension
    private final RealJenkinsExtension extension = new RealJenkinsExtension();

    @Test
    @SuppressWarnings("rawtypes")
    void smokes() throws Throwable {
        extension.then(r -> {
            String content = "title = \"Title\"\n[section]\nvalue = 1\n";
            TomlMapper mapper = TomlMapper.builder().build();
            Map data = mapper.readValue(content, Map.class);
            assertEquals("Title", data.get("title"));
            Map section = (Map) data.get("section");
            assertNotNull(section);
            assertEquals(1, section.get("value"));
        });
    }
}
