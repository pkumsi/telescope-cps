package com.cse564.telescope.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AdjustmentEvent extends ApplicationEvent {
    private final float adjustment;
    
    public AdjustmentEvent(Object source, float adjustment) {
        super(source);
        this.adjustment = adjustment;
    }
}