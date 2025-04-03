package com.nilsw13.springboot.replicate.unitaire;

import com.nilsw13.springboot.replicate.responsetype.prediction.Prediction;
import com.nilsw13.springboot.replicate.api.ReplicateRestClient;
import com.nilsw13.springboot.replicate.impl.PredictionServiceImpl;
import com.nilsw13.springboot.replicate.service.PredictionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("unit-test")
public class PredictionServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

    PredictionService predictionService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        predictionService = new PredictionServiceImpl(mockRestClient);
    }

    @Test
    void testGetters() {
        Map<String, Double> metrics= new HashMap<>();
        metrics.put("key", 2.35);

        Prediction prediction = new Prediction();
        prediction.setVersion("testversion");
        prediction.setError("null");
        prediction.setStatus("test status");
        prediction.setCompletedAt("2023-01-01");
        prediction.setDataRemoved(false);
        prediction.setSource("test-source");
        prediction.setStartedAt("12/02/12");
        prediction.setMetrics(metrics);


        assertThat(prediction.getVersion()).isEqualTo("testversion");

        assertThat(prediction.getCompletedAt()).isEqualTo("2023-01-01");
        assertThat(prediction.getDataRemoved()).isFalse();
        assertThat(prediction.getError()).isEqualTo("null");
        assertThat(prediction.getStatus()).isEqualTo("test status");
        assertThat(prediction.getSource()).isEqualTo("test-source");
        assertThat(prediction.getStartedAt()).isEqualTo("12/02/12");
        assertThat(prediction.getMetrics()).isNotNull();

    }
}
