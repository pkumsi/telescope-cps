package com.cse564.telescope.sa;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Getter
public class TargetDataSource {
    
    private float round = 0;
    
    public synchronized float getNextIntervalRot(List<Float> futureTargetRots) {
        int index = (int) round;
        
        // cyclic behavior handled
        if (index >= futureTargetRots.size()) {
            index = index % futureTargetRots.size();
        }
        
        float nextIntervalRot = futureTargetRots.get(index);
        log.debug("TargetDataSource A1: round={}, index={}, nextIntervalRot={}", 
                  round, index, nextIntervalRot);
        
        return nextIntervalRot;
    }
    
    public synchronized void updateRound(int roundsPerInterval) {
        round = round + roundsPerInterval;
        log.debug("TargetDataSource A2: Updated round={} (added roundsPerInterval={})", 
                  round, roundsPerInterval);
    }
    
    public synchronized float getRound() {
        return round;
    }
}
