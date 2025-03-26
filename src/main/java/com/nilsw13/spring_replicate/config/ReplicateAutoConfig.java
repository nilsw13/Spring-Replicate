package com.nilsw13.spring_replicate.config;

import com.nilsw13.spring_replicate.impl.*;
import com.nilsw13.spring_replicate.service.*;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.WebhookService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Spring Boot auto-configuration for the Replicate API client library.
 *
 * This configuration class automatically sets up all the necessary beans for
 * interacting with the Replicate API. It creates a complete service hierarchy
 * from the low-level REST client to the high-level service implementations,
 * all wired together appropriately.
 *
 * The auto-configuration is conditionally activated only when the "replicate.api-key"
 * property is present in the application configuration. This ensures that the
 * library components are only initialized when the necessary credentials are provided.
 *
 * All beans are annotated with @ConditionalOnMissingBean, allowing applications
 * to override any component with custom implementations if needed. This enables
 * flexibility while preserving the convenience of automatic configuration.
 *
 * Usage example in a Spring Boot application:
 *
 * 1. Add the dependency to your project
 * 2. Configure your API key in application.properties:
 *    replicate.api-key=your_api_key_here
 * 3. Inject the Replicate interface where needed via constructor injection:
 *
 *
 *    public class YourService {
 *        private final Replicate replicate;
 *
 *        public YourService(Replicate replicate) {
 *            this.replicate = replicate;
 *        }
 *
 *        public void someMethod() {
 *            // Now you can use the client
 *            ModelList models = replicate.models().list();
 *        }
 *    }
 *
 * @author Nilsw13
 * @since 1.0.0
 * @see ReplicateProperties
 * @see Replicate
 */
@Configuration
@EnableConfigurationProperties(ReplicateProperties.class)
@ConditionalOnProperty(prefix = "replicate", name = "api-key")
public class ReplicateAutoConfig {



    /**
     * Creates the core HTTP client for communicating with the Replicate API.
     *
     * This is the foundational component that handles all direct HTTP communication
     * with the API, including authentication, request formatting, and error handling.
     *
     * @param replicateProperties Configuration properties including API key and URL
     * @return A configured instance of the Replicate REST client
     */
    @Bean
    @ConditionalOnMissingBean
    public ReplicateRestClient replicateRestClient(ReplicateProperties replicateProperties) {
        return  new ReplicateRestClient(replicateProperties);
    }

    /**
     * Creates the account service for retrieving Replicate account information.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the AccountService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public AccountService accountService(ReplicateRestClient replicateRestClient) {
        return new AccountServiceImpl(replicateRestClient);
    }

    /**
     * Creates the webhook signing service for validating webhook requests.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the WebhookService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public WebhookService secretSigningWebhookService(ReplicateRestClient replicateRestClient) {
        return new WebhookServiceImpl(replicateRestClient);
    }


    /**
     * Creates the prediction service for running model predictions.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the PredictionService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public PredictionService predictionService(ReplicateRestClient replicateRestClient){
        return  new PredictionServiceImpl(replicateRestClient);
    }

    /**
     * Creates the model service for managing Replicate models.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the ModelService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public ModelService modelService(ReplicateRestClient replicateRestClient) {
        return new ModelServiceImpl(replicateRestClient);
    }

    /**
     * Creates the collection service for accessing model collections.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the CollectionService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public CollectionService collectionService(ReplicateRestClient replicateRestClient){
        return  new CollectionServiceImpl(replicateRestClient);
    }

    /**
     * Creates the deployment service for managing production deployments.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the DeploymentService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public DeploymentService deploymentService(ReplicateRestClient replicateRestClient){
        return new DeploymentServiceImpl(replicateRestClient);
    }

    /**
     * Creates the training service for fine-tuning models.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the TrainingService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public TrainingService trainingService(ReplicateRestClient replicateRestClient) {
        return  new TrainingServiceImpl(replicateRestClient);
    }


    /**
     * Creates the hardware service for retrieving available hardware options.
     *
     * @param replicateRestClient The REST client for API communication
     * @return An implementation of the HardwareService interface
     */
    @Bean
    @ConditionalOnMissingBean
    public HardwareService hardwareService(ReplicateRestClient replicateRestClient) {
        return new HardwareServiceImpl(replicateRestClient);
    }



    /**
     * Creates the main Replicate facade that provides access to all services.
     *
     * This bean serves as the primary entry point for applications using the Replicate
     * client. It aggregates all individual services into a cohesive API.
     *
     * @param accountService Service for account operations
     * @param webhookService Service for webhook signature verification
     * @param predictionService Service for prediction operations
     * @param modelService Service for model operations
     * @param collectionService Service for collection operations
     * @param deploymentService Service for deployment operations
     * @param trainingService Service for training operations
     * @param hardwareService Service for hardware information
     * @return A configured instance of the Replicate interface
     */
    @Bean
    @ConditionalOnMissingBean
    public Replicate replicate(AccountService accountService,
                               WebhookService webhookService,
                               PredictionService predictionService,
                               ModelService modelService,
                               CollectionService collectionService,
                               DeploymentService deploymentService,
                               TrainingService trainingService,
                               HardwareService hardwareService) {
        return new ReplicateService(accountService,
                webhookService,
                predictionService,
                modelService,
                collectionService,
                deploymentService,
                trainingService,
                hardwareService);
    }



}
