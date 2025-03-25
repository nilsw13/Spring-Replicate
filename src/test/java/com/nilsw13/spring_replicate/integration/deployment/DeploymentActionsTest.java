package com.nilsw13.spring_replicate.integration.deployment;

import com.nilsw13.spring_replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentConfiguration;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentList;
import com.nilsw13.spring_replicate.ResponseType.Prediction.Prediction;
import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeploymentActionsTest extends BaseReplicateTest {

    @Test
    void deploymentGetTest() {
        Deployment deployment = replicate.deployments().get("nilsw13", "deployment-test");
        System.out.println(deployment.getName());
        System.out.println(deployment.getCurrentRelease().getConfiguration().getHardware());
        System.out.println(deployment.getCurrentRelease().getConfiguration());
        assertThat(deployment.getName()).isEqualTo("deployment-test");
        assertThat(deployment.getOwner()).isEqualTo("nilsw13");
    }

    @Test
    void deploymentListTest() {
        DeploymentList deploymentList = replicate.deployments().list();
        System.out.println(deploymentList.getResults());
        assertThat(deploymentList).isNotNull();
    }

    @Test
    void updateDeploymentTest() {
        String owner = "nilsw13";
        String name = "my-test-librairie";
        DeploymentConfiguration configuration = new DeploymentConfiguration();
        configuration.setMinInstances(3);
        configuration.setMaxInstances(6);
        DeploymentConfiguration response = replicate.deployments().update(owner, name, configuration);
        System.out.println(response);
    }

    @Test
    void deleteDeploymentTest() {
        String owner = "nilsw13";
        String name = "deployment-test";
        Deployment response = replicate.deployments().delete(owner, name);
        System.out.println(response);

        assertThat(response).isNull();
    }

    @Test
    void createDeploymentTest() {
        DeploymentConfiguration body = new DeploymentConfiguration();
        body.setName("test-librairie-config");
        body.setModel("nilsw13/flux_hugh");
        body.setHardware("cpu");
        body.setMinInstances(2);
        body.setMaxInstances(6);
        body.setVersion("29b23acb903bace95fe737d3ef1935c6379b493e8111839cb33b6fe44c8b22f4");
        Deployment response = replicate.deployments().create(body);
        System.out.println(response.getCurrentRelease().getCreatedAt());
        System.out.println(response.getName());
        System.out.println(response.getOwner());
        System.out.println(response.getCurrentRelease().getModel());
    }

//    @Test
//    void createPredictionFromDeployment() throws InterruptedException {
//        Prediction prediction = replicate.deployments().createDeploymentPrediction("nilsw13", "test-librairie-config")
//                .input("prompt", "Hugh jackman at the beach")
//                .executeFromDeployment(true, 60);
//
//        System.out.println(prediction.getUrls());
//    }
}
