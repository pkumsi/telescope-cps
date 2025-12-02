package com.cse564.telescope.cyber;

import com.cse564.telescope.events.AdjustmentEvent;
import com.cse564.telescope.events.MakeAdjustmentEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

/**
 * MovementMaker Component - Based on SRC Diagram
 * State: Adjustment_Queue := empty, ca = 0, pa = 0
 * A1: idealAdjustment -> Adjustment_Queue
 * A2: currentRot, makeAdjustment, Adjustment_Queue, ca, pa -> adjustment, Adjustment_Queue, ca, pa
 */
@Component
@Slf4j
@Getter
public class MovementMaker {
    
    // State variables
    private final Queue<IdealAdjustment> Adjustment_Queue = new LinkedList<>();
    private float ca = 0;  // current (actual) adjustment command
    private float pa = 0;  // previous (actual) adjustment command
    
    private final ApplicationEventPublisher eventPublisher;
    
    public MovementMaker(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        log.info("MovementMaker initialized");
    }

    public synchronized void enqueueAdjustment(float idealAdjustment, float targetIdealPos) {
        Adjustment_Queue.add(new IdealAdjustment(idealAdjustment, targetIdealPos));
        log.debug("MovementMaker A1: Enqueued idealAdjustment={}, targetIdealPos={}, queue size={}", 
                  idealAdjustment, targetIdealPos, Adjustment_Queue.size());
    }
    

    @EventListener
    public synchronized void onMakeAdjustment(MakeAdjustmentEvent event) {
        log.debug("MovementMaker A2: makeAdjustment event received");
        
        if (!Adjustment_Queue.isEmpty()) {
            IdealAdjustment idealAdjustment = Adjustment_Queue.poll();
            float currentRot = getCurrentRotFromSensor();
            float previousAdjustment = ca;
            float adjustment = idealAdjustment.targetIdealPos() - currentRot;
            pa = previousAdjustment;
            ca = adjustment;
            
            log.info("MovementMaker A2: idealAdjustment={}, targetIdealPos={}, currentRot={}, adjustment={}, pa={}", 
                     idealAdjustment.idealAdjustment(), idealAdjustment.targetIdealPos(), currentRot, adjustment, pa);
            
            // Publish adjustment event
            eventPublisher.publishEvent(new AdjustmentEvent(this, adjustment));
        } else {
            log.debug("MovementMaker A2: Adjustment_Queue empty, adjustment=absent");
        }
    }
    
    // method to get current rotation from sensor
    // This will be injected via SAComp 
    private float currentRotFromSensor = 0;
    
    public void updateCurrentRot(float currentRot) {
        this.currentRotFromSensor = currentRot;
    }
    
    private float getCurrentRotFromSensor() {
        return currentRotFromSensor;
    }
    
    public synchronized int getQueueSize() {
        return Adjustment_Queue.size();
    }

    private record IdealAdjustment(float idealAdjustment, float targetIdealPos) { }
}
