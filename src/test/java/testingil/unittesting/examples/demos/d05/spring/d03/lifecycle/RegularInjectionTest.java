package testingil.unittesting.examples.demos.d05.spring.d03.lifecycle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes = { ItemRepositoryConfiguration.class })
public class RegularInjectionTest {

    @Autowired
    ItemRepository mockRepository;

    @Test
    public void test_one_call() {
        mockRepository.findTotal();
        verify(mockRepository).findTotal();
    }

    @Disabled
    @Test
    public void test_zero_calls() {
        verify(mockRepository,never()).findTotal();
    }
}
