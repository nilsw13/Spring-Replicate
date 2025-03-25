package com.nilsw13.spring_replicate.impl;


import com.nilsw13.spring_replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentConfiguration;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.DeploymentService;
import com.nilsw13.spring_replicate.service.PredictionBuilderService;
import org.springframework.stereotype.Service;

@Service
public class DeploymentServiceImpl implements DeploymentService {

    private final ReplicateRestClient replicateRestClient;
    private String modelVersion;

    public DeploymentServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }


    @Override
    public Deployment create(DeploymentConfiguration configuration) {
        return replicateRestClient.post("deployments", configuration, Deployment.class);
    }

    @Override
    public PredictionBuilderService createDeploymentPrediction(String deploymentOwner, String deploymentName) {
        return new PredictionBuilderServiceImpl(replicateRestClient, modelVersion, deploymentOwner, deploymentName);
    }

    @Override
    public Deployment get(String deploymentOwner, String deploymentName) {
        return replicateRestClient.get("deployments/" + deploymentOwner + "/" + deploymentName, Deployment.class);
    }

    @Override
    public DeploymentList list() {
        return replicateRestClient.get("deployments", DeploymentList.class );
    }

    @Override
    public DeploymentConfiguration update(String owner, String name, DeploymentConfiguration changes) {
        return replicateRestClient.patch("deployments/" + owner + "/" + name, changes, DeploymentConfiguration.class);
    }

    @Override
    public Deployment delete(String owner, String name) {
        return replicateRestClient.delete("deployments/" + owner + "/" + name, Deployment.class );
    }
}
