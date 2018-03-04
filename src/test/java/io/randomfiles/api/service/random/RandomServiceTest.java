package io.randomfiles.api.service.random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RandomService.class})
public class RandomServiceTest {

    @Inject
    private RandomService randomService;

    @Test
    public void getRandomStringTest() {
        String randomString1 = randomService.getRandomString();
        String randomString2 = randomService.getRandomString();

        Assert.assertNotEquals(randomString1, randomString2);
    }
}
