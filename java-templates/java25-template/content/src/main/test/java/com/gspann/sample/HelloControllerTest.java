package com.gspann.sample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

class HelloControllerTest {

    @Test
    void returnsGreetingWithActiveSpringProfile() {
        new WebApplicationContextRunner()
                .withUserConfiguration(SampleAppApplication.class)
                .withPropertyValues("spring.profiles.active=dev")
                .run(context -> {
                    HelloController controller = context.getBean(HelloController.class);

                    assertThat(controller.hello()).isEqualTo("Hellow GSPANN , Env: dev");
                });
    }

    @Test
    void usesLocalWhenNoSpringProfileIsActive() {
        new WebApplicationContextRunner()
                .withUserConfiguration(SampleAppApplication.class)
                .run(context -> {
                    HelloController controller = context.getBean(HelloController.class);

                    assertThat(controller.hello()).isEqualTo("Hellow GSPANN , Env: local");
                });
    }
}
