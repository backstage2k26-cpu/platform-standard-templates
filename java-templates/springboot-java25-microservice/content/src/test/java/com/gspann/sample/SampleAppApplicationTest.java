package com.gspann.sample;

import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

class SampleAppApplicationTest {

    @Test
    void startsSpringBootApplication() {
        String[] arguments = {"--spring.profiles.active=dev"};

        try (MockedStatic<SpringApplication> springApplication = mockStatic(SpringApplication.class)) {
            SampleAppApplication.main(arguments);

            springApplication.verify(() -> SpringApplication.run(SampleAppApplication.class, arguments));
        }
    }
}
