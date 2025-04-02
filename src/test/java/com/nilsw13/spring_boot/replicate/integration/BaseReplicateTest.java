package com.nilsw13.spring_boot.replicate.integration;

import com.nilsw13.spring_boot.replicate.config.ReplicateAutoConfig;
import com.nilsw13.spring_boot.replicate.service.Replicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ReplicateAutoConfig.class)
public abstract class BaseReplicateTest {

    @Autowired
    public Replicate replicate;
}
