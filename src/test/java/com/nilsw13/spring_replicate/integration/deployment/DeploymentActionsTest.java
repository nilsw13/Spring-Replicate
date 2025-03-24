package com.nilsw13.spring_replicate.integration.deployment;

import com.nilsw13.spring_replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentList;
import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeploymentActionsTest extends BaseReplicateTest {

    @Test
    void deploymentGetTest() {
        Deployment deployment = replicate.deployments().get("nilsw13", "deployment-test");
        System.out.println(deployment.getName());
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
}
