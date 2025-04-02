package com.nilsw13.spring_boot.replicate.integration;

import com.nilsw13.spring_boot.replicate.ResponseType.Hardware.Hardware;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("integration-test")
public class HardwareActionsTest extends BaseReplicateTest {

    @Test
    void hardwareListTest() {
        Hardware[] list = replicate.hardware().list();

        assertThat(list).isNotNull();
        assertThat(Arrays.stream(list).toList()).isNotNull();
    }
}
