package com.nilsw13.spring_replicate.service.Deployments;

import com.nilsw13.spring_replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentConfiguration;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentList;
import com.nilsw13.spring_replicate.service.prediction.PredictionBuilderService;

import java.util.Map;

public interface DeploymentService {

    Deployment create(DeploymentConfiguration configuration );
    PredictionBuilderService createDeploymentPrediction(String deploymentOwner, String deploymentName);
    Deployment get(String deploymentOwner, String deploymentName);
    DeploymentList list();
    DeploymentConfiguration update(String owner, String name, DeploymentConfiguration changes);
    Deployment delete(String owner, String name);
}
