package com.nilsw13.spring_replicate.impl;

import com.nilsw13.spring_replicate.ResponseType.Hardware.Hardware;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.service.HardwareService;

public class HardwareServiceImpl implements HardwareService {

    private final ReplicateRestClient replicateRestClient;

    public HardwareServiceImpl(ReplicateRestClient replicateRestClient) {
        this.replicateRestClient = replicateRestClient;
    }

    @Override
    public Hardware[] list() {
        return replicateRestClient.get("hardware", Hardware[].class);
    }
}
