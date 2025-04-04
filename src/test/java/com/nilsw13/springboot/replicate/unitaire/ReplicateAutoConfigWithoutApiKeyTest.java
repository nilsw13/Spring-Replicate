package com.nilsw13.springboot.replicate.unitaire;

import com.nilsw13.springboot.replicate.api.ReplicateRestClient;
import com.nilsw13.springboot.replicate.config.ReplicateAutoConfig;
import com.nilsw13.springboot.replicate.service.Replicate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit-test")
 class ReplicateAutoConfigWithoutApiKeyTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ReplicateAutoConfig.class));

    @Test
     void shouldNotCreateBeansWhenApiKeyIsMissing() {
        contextRunner
                .withPropertyValues("replicate.api-key=")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(ReplicateRestClient.class);
                    assertThat(context).doesNotHaveBean(Replicate.class);
                });

    }
}
