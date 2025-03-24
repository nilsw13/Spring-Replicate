package com.nilsw13.spring_replicate.impl;


import com.nilsw13.spring_replicate.ResponseType.Deployment.Deployment;
import com.nilsw13.spring_replicate.ResponseType.Deployment.DeploymentList;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.Deployments.DeploymentService;
import org.springframework.stereotype.Service;

@Service
public class DeploymentServiceImpl implements DeploymentService {

    private final ReplicateRestClient replicateRestClient;

    public DeploymentServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }

    @Override
    public Deployment get(String deploymentOwner, String deploymentName) {
        return replicateRestClient.get("deployments/" + deploymentOwner + "/" + deploymentName, Deployment.class);
    }

    @Override
    public DeploymentList list() {
        return replicateRestClient.get("deployments", DeploymentList.class );
    }
}
