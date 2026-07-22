package com.gspann.sample;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    @ParameterizedTest
    @MethodSource("supportedProfiles")
    void returnsGreetingForEachDeploymentProfile(String profile) {
        new WebApplicationContextRunner()
                .withUserConfiguration(SampleAppApplication.class)
                .withPropertyValues("spring.profiles.active=" + profile)
                .run(context -> {
                    HelloController controller = context.getBean(HelloController.class);

                    assertThat(controller.hello()).isEqualTo("Hellow GSPANN , Env: " + profile);
                    assertThat(controller.home()).isEqualTo("Hellow GSPANN , Env: " + profile);
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

    private static Stream<Arguments> supportedProfiles() {
        return Stream.of(Arguments.of("dev"), Arguments.of("qa"), Arguments.of("prod"));
    }
}
