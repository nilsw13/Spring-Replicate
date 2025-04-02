package com.nilsw13.spring_boot.replicate.unitaire;

import com.nilsw13.spring_boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring_boot.replicate.config.ReplicateAutoConfig;
import com.nilsw13.spring_boot.replicate.service.Replicate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit-test")
public class ReplicateAutoConfigTestWithoutApiKey {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ReplicateAutoConfig.class));

    @Test
    public void shouldNotCreateBeansWhenApiKeyIsMissing() {
        contextRunner
                .withPropertyValues("replicate.api.key")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(ReplicateRestClient.class);
                    assertThat(context).doesNotHaveBean(Replicate.class);
                });

    }
}
