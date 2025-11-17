package com.cse564.telescope.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RotateTelescopeEvent extends ApplicationEvent {
    private final float rotateTelescope;
    
    public RotateTelescopeEvent(Object source, float rotateTelescope) {
        super(source);
        this.rotateTelescope = rotateTelescope;
    }
}