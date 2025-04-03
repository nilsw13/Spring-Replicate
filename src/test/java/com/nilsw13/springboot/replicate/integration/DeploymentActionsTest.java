package com.nilsw13.springboot.replicate.integration;

import com.nilsw13.springboot.replicate.responsetype.deployment.Deployment;
import com.nilsw13.springboot.replicate.responsetype.deployment.DeploymentConfiguration;
import com.nilsw13.springboot.replicate.responsetype.deployment.DeploymentList;
import com.nilsw13.springboot.replicate.responsetype.prediction.Prediction;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@Tag("integration-test")
 class DeploymentActionsTest extends BaseReplicateTest {

    private static final String TEST_DEPLOYMENT_NAME = "testdeployment-1743461944585";

    private static final String TEST_DEPLOYMENT_OWNER = "nilsw13";

    private final String nameToUse = "testdeployment-" + System.currentTimeMillis();





    @Test
    void deploymentGetTest() {


        Deployment deployment = replicate.deployments().get(TEST_DEPLOYMENT_OWNER, TEST_DEPLOYMENT_NAME);
        assertThat(deployment).isNotNull();
        assertThat(deployment.getName()).isEqualTo(TEST_DEPLOYMENT_NAME);
        assertThat(deployment.getOwner()).isEqualTo(TEST_DEPLOYMENT_OWNER);
    }

    @Test
    void deploymentListTest() {
        DeploymentList deploymentList = replicate.deployments().list();
        System.out.println(deploymentList.getResults());
        assertThat(deploymentList).isNotNull();
        assertThat(deploymentList.getResults()).isNotNull();
        assertThat(deploymentList.getPrevious()).isNull();
        assertThat(deploymentList.getNext()).isNotNull();
    }

    @Test
    void updateDeploymentTest() throws InterruptedException {
        try {
            Deployment currentDeployment = replicate.deployments().get(TEST_DEPLOYMENT_OWNER, TEST_DEPLOYMENT_NAME);
            int currentMaxInstances = currentDeployment.getCurrentRelease().getConfiguration().getMaxInstances();


            int newMaxInstances = (currentMaxInstances == 2) ? 3 : 2;

            DeploymentConfiguration configuration = new DeploymentConfiguration();
            configuration.setMinInstances(1);
            configuration.setMaxInstances(newMaxInstances);

            replicate.deployments().update(TEST_DEPLOYMENT_OWNER, TEST_DEPLOYMENT_NAME, configuration);

            Thread.sleep(3000);

            Deployment updatedDeployment = replicate.deployments().get(TEST_DEPLOYMENT_OWNER, TEST_DEPLOYMENT_NAME);
            int actualMaxInstances = updatedDeployment.getCurrentRelease().getConfiguration().getMaxInstances();

            assertThat(actualMaxInstances).isEqualTo(newMaxInstances);

        } catch (Exception e) {
            System.out.println("Erreur lors du test: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }




    @Test
    void createDeploymentTest() {



        DeploymentConfiguration config = new DeploymentConfiguration();
        config.setName(nameToUse);
        config.setModel("nilsw13/flux_hugh");
        config.setHardware("cpu");
        config.setMinInstances(1);
        config.setMaxInstances(2);
        config.setVersion("29b23acb903bace95fe737d3ef1935c6379b493e8111839cb33b6fe44c8b22f4");


        Deployment response = replicate.deployments().create(config);
        assertThat(response).isNotNull();
    }

    @Test
    void createPredictionFromDeployment() throws InterruptedException {
        Prediction prediction = replicate.deployments().createDeploymentPrediction("nilsw13", TEST_DEPLOYMENT_NAME)
                .input("prompt", "Hugh jackman at the beach")
                .executeFromDeployment(false);

        assertThat(prediction.getUrls()).isNotNull();
    }
}
