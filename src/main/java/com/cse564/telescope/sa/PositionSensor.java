package com.cse564.telescope.sa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PositionSensor {

    public float readCurrentRot(float currentRotRaw) {
        float currentRot = currentRotRaw;
        log.debug("PositionSensor A1: currentRotRaw={}, currentRot={}", currentRotRaw, currentRot);
        return currentRot;
    }
}
