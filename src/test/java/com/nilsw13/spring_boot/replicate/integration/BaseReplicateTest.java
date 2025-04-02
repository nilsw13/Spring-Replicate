package com.nilsw13.spring_boot.replicate.integration;

import com.nilsw13.spring_boot.replicate.config.ReplicateAutoConfig;
import com.nilsw13.spring_boot.replicate.service.Replicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(classes = ReplicateAutoConfig.class)
@TestPropertySource(properties = {
        "replicate.api.key=${REPLICATE_API_KEY:dummy-test-key}"
})
public abstract class BaseReplicateTest {

    @Autowired
    public Replicate replicate;
}
