package com.nilsw13.spring_replicate.service.Deployments;

import com.nilsw13.spring_replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentList;

public interface DeploymentService {

    Deployment get(String deploymentOwner, String deploymentName);
    DeploymentList list();
}
