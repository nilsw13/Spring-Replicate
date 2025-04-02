package com.nilsw13.spring_boot.replicate.unitaire;
import com.nilsw13.spring_boot.replicate.ResponseType.Hardware.Hardware;
import com.nilsw13.spring_boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring_boot.replicate.impl.HardwareServiceImpl;
import com.nilsw13.spring_boot.replicate.service.HardwareService;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals; // Manquant
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@Tag("unit-test")
public class HardwareServiceImplTest {

    @Mock
    private ReplicateRestClient mockReplicateRestClient;

    private HardwareService hardwareService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        hardwareService = new HardwareServiceImpl(mockReplicateRestClient);
    }

    @Test
    public void testSetterAndGetter() {
        Hardware hardware = new Hardware();
        hardware.setName("hardware test");
        hardware.setSku("sku test");

        assertThat(hardware.getName()).isEqualTo("hardware test");
        assertThat(hardware.getSku()).isEqualTo("sku test");
    }

    @Test
    public void testGet_shouldCallEndpointAndReturnHHardwareList() {
        Hardware[] hardwares = new Hardware[2];
        hardwares[0] = new Hardware("cpu", "cpu-sku");
        hardwares[1] = new Hardware("cpu-2", "cpu-sku-2");

        Mockito.when(mockReplicateRestClient.get("hardware", Hardware[].class)).thenReturn(hardwares);

        Hardware[] result = hardwareService.list();

        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.length, "Result should contain 2 hardware items");
        assertEquals("cpu", result[0].getName(), "First hardware name should match");
        assertEquals("cpu-sku", result[0].getSku(), "First hardware SKU should match");
        assertEquals("cpu-2", result[1].getName(), "Second hardware name should match");
        assertEquals("cpu-sku-2", result[1].getSku(), "Second hardware SKU should match");
    }
}