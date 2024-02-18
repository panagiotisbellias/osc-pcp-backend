package gr.pcp.caching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DefaultCacheKeyGeneratorTest {

    @InjectMocks
    DefaultCacheKeyGenerator defaultCacheKeyGenerator;

    @Test
    void testGenerate() {
        Object object = Mockito.mock(Object.class);
        Object actualObject = defaultCacheKeyGenerator.generate(null, object);
        Assertions.assertInstanceOf(String.class, actualObject);
        Assertions.assertEquals("all", actualObject);
    }

}
