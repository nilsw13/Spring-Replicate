package com.nilsw13.spring_boot.replicate.integration;

import com.nilsw13.spring_boot.replicate.ResponseType.Hardware.Hardware;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Tag("integration-test")
public class HardwareActionsTest extends BaseReplicateTest {

    @Test
    void hardwareListTest() {
        Hardware[] list = replicate.hardware().list();
        System.out.println(Arrays.stream(list).toList());
    }
}
