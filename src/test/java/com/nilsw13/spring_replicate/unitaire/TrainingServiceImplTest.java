package com.nilsw13.spring_replicate.unitaire;

import com.nilsw13.spring_replicate.ResponseType.Training.Training;
import com.nilsw13.spring_replicate.api.ReplicateRestClient;
import com.nilsw13.spring_replicate.impl.TrainingBuilderServiceImpl;
import com.nilsw13.spring_replicate.impl.TrainingServiceImpl;
import com.nilsw13.spring_replicate.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("unit-test")
public class TrainingServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;

    private TrainingService trainingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        trainingService = new TrainingServiceImpl(mockRestClient);
    }

    @Test
    void testGetters() {
        Map<String, String> input = new HashMap<>();
        input.put("input-images", "image test.png");
        Map<String, Double> metrics = new HashMap<>();
        metrics.put("test metrics", 2.36);
        Training train = new Training();
        train.setVersion("test version");
        train.setMetrics(metrics);
        train.setLogs("tets logs");
        train.setInput(input);
        train.setError("null");
        train.setCreatedAt("12/12/12");
        train.setCompletedAt("12/13/13");

        assertThat(train.getVersion()).isEqualTo("test version");
        assertThat(train.getMetrics()).isEqualTo(metrics);
        assertThat(train.getLogs()).isEqualTo("tets logs");
        assertThat(train.getInput()).isEqualTo(input);
        assertThat(train.getError()).isEqualTo("null");
        assertThat(train.getCreatedAt()).isEqualTo("12/12/12");
        assertThat(train.getCompletedAt()).isEqualTo("12/13/13");

    }

    private static class TestableTrainingBuilder extends TrainingBuilderServiceImpl {
        // Variables pour capturer les arguments
        public boolean capturedWait;
        public int capturedTimeout;
        public int executeOverloadCallCount = 0;
        public Training mockTraining = new Training(); // Ou créez un mock selon vos besoins

        /**
         * Constructs a new TrainingBuilderServiceImpl with the required dependencies.
         *
         * @param replicateRestClient The REST client for API communication
         * @param modelOwner          The username or organization that owns the source model
         * @param modelName           The name of the source model
         * @param modelversion        The version ID of the source model
         */
        public TestableTrainingBuilder(ReplicateRestClient replicateRestClient, String modelOwner, String modelName, String modelversion) {
            super(replicateRestClient, modelOwner, modelName, modelversion);
        }

        @Override
        public Training execute(boolean wait, int timeout) throws InterruptedException {
            // Capturer les arguments
            capturedWait = wait;
            capturedTimeout = timeout;
            executeOverloadCallCount++;

            // Retourner un objet Training prédéfini
            return mockTraining;
        }
    }



}
