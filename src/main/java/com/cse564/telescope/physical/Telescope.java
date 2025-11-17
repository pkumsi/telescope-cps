package com.cse564.telescope.physical;

import com.cse564.telescope.events.RotateTelescopeEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
public class Telescope {
    
    private float rotation = 0.0f;
    
    public synchronized float getCurrentRotRaw() {
        log.debug("Telescope A1: currentRotRaw={}", rotation);
        return rotation;
    }
    
    @EventListener
    public synchronized void onRotateTelescope(RotateTelescopeEvent event) {
        float rotateTelescope = event.getRotateTelescope();
        rotation = rotation + rotateTelescope;
        
        log.info("Telescope A2: rotateTelescope={}, new rotation={}", rotateTelescope, rotation);
    }
    
    public synchronized float getRotation() {
        return rotation;
    }
}
