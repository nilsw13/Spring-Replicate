package com.nilsw13.spring_boot.replicate.unitaire;


import com.nilsw13.spring_boot.replicate.ResponseType.Model.Model;
import com.nilsw13.spring_boot.replicate.ResponseType.Model.ModelList;
import com.nilsw13.spring_boot.replicate.ResponseType.Model.ModelVersionList;
import com.nilsw13.spring_boot.replicate.ResponseType.Model.Version;
import com.nilsw13.spring_boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring_boot.replicate.config.ReplicateAutoConfig;
import com.nilsw13.spring.boot.replicate.impl.*;
import com.nilsw13.spring.boot.replicate.service.*;
import com.nilsw13.spring_boot.replicate.impl.*;
import com.nilsw13.spring_boot.replicate.service.*;
import com.nilsw13.spring_replicate.impl.*;
import com.nilsw13.spring_replicate.service.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
@Tag("unit-test")
public class ReplicateAutoConfigTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ReplicateAutoConfig.class));

    @Test
    public void shouldNotCreateBeansWhenApiKeyIsMissing() {
        contextRunner
                .run(context -> {
                    assertThat(context).doesNotHaveBean(ReplicateRestClient.class);
                    assertThat(context).doesNotHaveBean(Replicate.class);
                });
    }

    @Test
    public void shouldCreateBeansWhenApiKeyIsPresent() {
        contextRunner
                .withPropertyValues("replicate.api-key=test-api-key")
                .run(context -> {
                    assertThat(context).hasSingleBean(ReplicateRestClient.class);
                    assertThat(context).hasSingleBean(Replicate.class);
                    assertThat(context).hasSingleBean(AccountService.class);
                    assertThat(context).hasSingleBean(WebhookService.class);
                    assertThat(context).hasSingleBean(ModelService.class);
                    assertThat(context).hasSingleBean(PredictionService.class);
                    assertThat(context).hasSingleBean(CollectionService.class);
                    assertThat(context).hasSingleBean(DeploymentService.class);
                    assertThat(context).hasSingleBean(TrainingService.class);
                    assertThat(context).hasSingleBean(HardwareService.class);

                    assertThat(context.getBean(AccountService.class)).isInstanceOf(AccountServiceImpl.class);
                    assertThat(context.getBean(ModelService.class)).isInstanceOf(ModelServiceImpl.class);
                    assertThat(context.getBean(WebhookService.class)).isInstanceOf(WebhookServiceImpl.class);
                    assertThat(context.getBean(ModelService.class)).isInstanceOf(ModelServiceImpl.class);
                    assertThat(context.getBean(PredictionService.class)).isInstanceOf(PredictionServiceImpl.class);
                    assertThat(context.getBean(CollectionService.class)).isInstanceOf(CollectionServiceImpl.class);
                    assertThat(context.getBean(DeploymentService.class)).isInstanceOf(DeploymentServiceImpl.class);
                    assertThat(context.getBean(TrainingService.class)).isInstanceOf(TrainingServiceImpl.class);
                    assertThat(context.getBean(HardwareService.class)).isInstanceOf(HardwareServiceImpl.class);

                });



    }

    @Test
    public void shouldRespectCustomBeans() {
        contextRunner
                .withPropertyValues("replicate.api-key=test-api-key")
                .withUserConfiguration(CustomModelServiceConfig.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(ModelService.class);
                    assertThat(context.getBean(ModelService.class)).isInstanceOf(CustomModelService.class);
                });
    }


    static class CustomModelServiceConfig {
        @Bean
        public ModelService modelService() {
            return new CustomModelService();
        }
    }

    static class CustomModelService implements ModelService {
        @Override
        public Model create(Model request) {
            return null;
        }

        @Override
        public PredictionBuilderService createModelPrediction(String modelOwner, String modelName) {
            return null;
        }

        @Override
        public Model get(String modelOwner, String modelName) {
            return null;
        }

        @Override
        public Model delete(String modelOwner, String modelName) {
            return null;
        }

        @Override
        public ModelList list() {
            return null;
        }

        @Override
        public ModelVersionList listModelVersions(String modelOwner, String modelName) {
            return null;
        }

        @Override
        public Version getModelVersion(String modelOwner, String modelName, String versionId) {
            return null;
        }

        @Override
        public Version deleteModelVersion(String modelOwner, String modelName, String versionId) {
            return null;
        }

        @Override
        public String getModelReadme(String modelOwner, String modelName) {
            return "";
        }
    }
}
