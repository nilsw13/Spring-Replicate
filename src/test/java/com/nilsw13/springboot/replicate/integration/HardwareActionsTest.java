package com.nilsw13.springboot.replicate.integration;

import com.nilsw13.springboot.replicate.responsetype.hardware.Hardware;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("integration-test")
 class HardwareActionsTest extends BaseReplicateTest {

    @Test
    void hardwareListTest() {
        Hardware[] list = replicate.hardware().list();

        assertThat(list).isNotNull();
        assertThat(Arrays.stream(list).toList()).isNotNull();
    }
}
