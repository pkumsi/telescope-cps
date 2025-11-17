package com.cse564.telescope.cyber;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

// MovementCalculator Component
// State: float idealPos = 0
// A1: roundsPerInterval_in -> roundsPerInterval_out
// A2: nextIntervalRot, idealPos -> idealAdjustment, idealPos

@Component
@Slf4j
@Getter
public class MovementCalculator {
    
    // State variable
    private float idealPos = 0;

    // A1: roundsPerInterval_in -> roundsPerInterval_out

    public int roundsPerInterval_in(int roundsPerInterval_in) {
        int roundsPerInterval_out = roundsPerInterval_in;
        log.debug("MovementCalculator A1: roundsPerInterval_out={}", roundsPerInterval_out);
        return roundsPerInterval_out;
    }

    // A2: nextIntervalRot, idealPos -> idealAdjustment, idealPos
    // idealAdjustment := nextIntervalRot - idealPos
    // idealPos := nextIntervalRot

    public synchronized float calculateIdealAdjustment(float nextIntervalRot) {
        float idealAdjustment = nextIntervalRot - idealPos;
        idealPos = nextIntervalRot;
        
        log.debug("MovementCalculator A2: nextIntervalRot={}, idealAdjustment={}, new idealPos={}", 
                  nextIntervalRot, idealAdjustment, idealPos);
        
        return idealAdjustment;
    }
    
    public synchronized float getIdealPos() {
        return idealPos;
    }
}