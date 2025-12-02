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
    private int intervalIndex = 0;
    
    public synchronized float getNextIntervalRot(List<Float> futureTargetRots) {
        if (futureTargetRots == null || futureTargetRots.isEmpty()) {
            throw new IllegalArgumentException("futureTargetRots must contain at least one value");
        }
        
        int size = futureTargetRots.size();
        int index = intervalIndex % size;
        
        float nextIntervalRot = futureTargetRots.get(index);
        log.debug("TargetDataSource A1: round={}, intervalIndex={}, index={}, nextIntervalRot={}", 
                  round, intervalIndex, index, nextIntervalRot);
        
        return nextIntervalRot;
    }
    
    public synchronized void updateRound(int roundsPerInterval) {
        round = round + roundsPerInterval;
        intervalIndex = intervalIndex + 1;
        log.debug("TargetDataSource A2: Updated round={} (added roundsPerInterval={}), intervalIndex={}", 
                  round, roundsPerInterval, intervalIndex);
    }
    
    public synchronized float getRound() {
        return round;
    }
    
    public synchronized int getIntervalIndex() {
        return intervalIndex;
    }
}
