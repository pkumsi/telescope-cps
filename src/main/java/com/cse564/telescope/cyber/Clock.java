package com.cse564.telescope.cyber;

import com.cse564.telescope.events.MakeAdjustmentEvent;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Clock {

    private int rpi;
    private int rounds = 0;

    private final ApplicationEventPublisher eventPublisher;

    public Clock(ApplicationEventPublisher eventPublisher,
                 @Value("${clock.rpi:10}") int rpi) {
        this.eventPublisher = eventPublisher;
        this.rpi = rpi;
        log.info("Clock initialized with rpi={}", rpi);
    }
    
    @Scheduled(fixedDelayString = "${clock.interval.ms:1000}")
    public void tick() {
        rounds = rounds + 1;
        int roundsPerInterval = rpi;
        
        log.debug("Clock A1: rounds={}, roundsPerInterval={}", rounds, roundsPerInterval);
        
        if (rounds >= rpi) {
            rounds = 0;
            log.info("Clock A2: Triggering makeAdjustment event (rounds reset to 0)");
            eventPublisher.publishEvent(new MakeAdjustmentEvent(this));
        }
    }
    
    public int getRpi() {
        return rpi;
    }
    
    public int getRoundsPerInterval() {
        return rpi;
    }
    
    public synchronized int getCurrentRounds() {
        return rounds;
    }
}
