package com.cse564.telescope.orchestration;

import com.cse564.telescope.cyber.Clock;
import com.cse564.telescope.cyber.MovementCalculator;
import com.cse564.telescope.cyber.MovementMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//CyberComp - Orchestrates Cyber Components
@Component
@Slf4j
@RequiredArgsConstructor
public class CyberComp {
    
    private final Clock clock;
    private final MovementCalculator movementCalculator;
    private final MovementMaker movementMaker;
    
    //Process nextIntervalRot from SAComp

    public void processNextIntervalRot(float nextIntervalRot) {
        float idealAdjustment = movementCalculator.calculateIdealAdjustment(nextIntervalRot);
        
        movementMaker.enqueueAdjustment(idealAdjustment, movementCalculator.getIdealPos());
        
        log.debug("CyberComp: Processed nextIntervalRot={}, idealAdjustment={}", 
                  nextIntervalRot, idealAdjustment);
    }
    

    public int getRoundsPerInterval() {
        return clock.getRoundsPerInterval();
    }

    public void updateCurrentRot(float currentRot) {
        movementMaker.updateCurrentRot(currentRot);
    }
}
