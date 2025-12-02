// package com.cse564.telescope.orchestration;

// import com.cse564.telescope.events.MakeAdjustmentEvent;
// import com.cse564.telescope.sa.Motor;
// import com.cse564.telescope.sa.PositionSensor;
// import com.cse564.telescope.sa.TargetDataSource;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.context.event.EventListener;
// import org.springframework.stereotype.Component;

// import java.util.List;


// @Component
// @Slf4j
// @RequiredArgsConstructor
// public class SAComp {
    
//     private final TargetDataSource targetDataSource;
//     private final PositionSensor positionSensor;
//     private final Motor motor;
//     private final PhysicalComp physicalComp;
//     private final CyberComp cyberComp;

//     @EventListener
//     public void onMakeAdjustment(MakeAdjustmentEvent event) {
//         log.info("SAComp: makeAdjustment event received, orchestrating system flow");
        
//         List<Float> futureTargetRots = physicalComp.getFutureTargetRots();
        
//         float nextIntervalRot = targetDataSource.getNextIntervalRot(futureTargetRots);
        
//         int roundsPerInterval = cyberComp.getRoundsPerInterval();
//         targetDataSource.updateRound(roundsPerInterval);
        
//         cyberComp.processNextIntervalRot(nextIntervalRot);
        
//         float currentRotRaw = physicalComp.getCurrentRotRaw();
//         float currentRot = positionSensor.readCurrentRot(currentRotRaw);
        
//         cyberComp.updateCurrentRot(currentRot);
        
//         log.info("SAComp: Orchestration complete - nextIntervalRot={}, currentRot={}", 
//                  nextIntervalRot, currentRot);
//     }
// }


package com.cse564.telescope.orchestration;

import com.cse564.telescope.events.MakeAdjustmentEvent;
import com.cse564.telescope.sa.Motor;
import com.cse564.telescope.sa.PositionSensor;
import com.cse564.telescope.sa.TargetDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SAComp {
    
    private final TargetDataSource targetDataSource;
    private final PositionSensor positionSensor;
    private final Motor motor;
    private final PhysicalComp physicalComp;
    private final CyberComp cyberComp;

    @EventListener
    public void onMakeAdjustment(MakeAdjustmentEvent event) {
        log.info("SAComp: makeAdjustment event received, orchestrating system flow");
        
        //Read and update current position 1st
        float currentRotRaw = physicalComp.getCurrentRotRaw();
        float currentRot = positionSensor.readCurrentRot(currentRotRaw);
        cyberComp.updateCurrentRot(currentRot);
        
        log.debug("SAComp: Updated currentRot to {}", currentRot);
        
        List<Float> futureTargetRots = physicalComp.getFutureTargetRots();
        float nextIntervalRot = targetDataSource.getNextIntervalRot(futureTargetRots);
        
        int roundsPerInterval = cyberComp.getRoundsPerInterval();
        targetDataSource.updateRound(roundsPerInterval);
        
        cyberComp.processNextIntervalRot(nextIntervalRot);
        
        log.info("SAComp: Orchestration complete - nextIntervalRot={}, currentRot={}", 
                 nextIntervalRot, currentRot);
    }
}
