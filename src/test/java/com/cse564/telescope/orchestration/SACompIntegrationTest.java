package com.cse564.telescope.orchestration;

import com.cse564.telescope.cyber.MovementCalculator;
import com.cse564.telescope.cyber.MovementMaker;
import com.cse564.telescope.events.MakeAdjustmentEvent;
import com.cse564.telescope.sa.TargetDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.task.scheduling.enabled=false")
class SACompIntegrationTest {

    @Autowired
    private SAComp saComp;

    @Autowired
    private MovementMaker movementMaker;

    @Autowired
    private MovementCalculator movementCalculator;

    @Autowired
    private TargetDataSource targetDataSource;

    @Test
    void orchestratesNextIntervalAndQueuesAdjustment() {
        assertEquals(0, movementMaker.getQueueSize());
        assertEquals(0.0f, movementCalculator.getIdealPos());
        assertEquals(0.0f, targetDataSource.getRound());
        assertEquals(0, targetDataSource.getIntervalIndex());

        saComp.onMakeAdjustment(new MakeAdjustmentEvent(this));

        assertEquals(1, movementMaker.getQueueSize());
        assertEquals(10.0f, movementCalculator.getIdealPos());
        assertEquals(10.0f, targetDataSource.getRound());
        assertEquals(1, targetDataSource.getIntervalIndex());
    }
}
