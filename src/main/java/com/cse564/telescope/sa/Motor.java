package com.cse564.telescope.sa;

import com.cse564.telescope.events.AdjustmentEvent;
import com.cse564.telescope.events.RotateTelescopeEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
@Getter
public class Motor {
    
    private final float error;
    
    private final ApplicationEventPublisher eventPublisher;
    private final Random random = new Random();
    
    public Motor(ApplicationEventPublisher eventPublisher,
                 @Value("${motor.error:1.0}") float error) {
        this.eventPublisher = eventPublisher;
        this.error = error;
        log.info("Motor initialized with error={}", error);
    }
    
  
    @EventListener
    public void onAdjustment(AdjustmentEvent event) {
        float adjustment = event.getAdjustment();
        
        // gaussian distribution with mean=0, stddev=error/3 
        float noise = (float) (random.nextGaussian() * (error / 3.0));
        
        // clamp noise to [-error, error]
        noise = Math.max(-error, Math.min(error, noise));
        
        float rotateTelescope = adjustment + noise;
        
        log.info("Motor A1: adjustment={}, noise={}, rotateTelescope={}", 
                 adjustment, noise, rotateTelescope);
        
        eventPublisher.publishEvent(new RotateTelescopeEvent(this, rotateTelescope));
    }
}
