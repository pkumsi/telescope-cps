package com.cse564.telescope.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MakeAdjustmentEvent extends ApplicationEvent {
    public MakeAdjustmentEvent(Object source) {
        super(source);
    }
}