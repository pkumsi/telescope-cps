package com.cse564.telescope.service;

import com.cse564.telescope.cyber.Clock;
import com.cse564.telescope.cyber.MovementCalculator;
import com.cse564.telescope.cyber.MovementMaker;
import com.cse564.telescope.physical.Telescope;
import com.cse564.telescope.sa.TargetDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemStatePublisher {
    
    private final SimpMessagingTemplate messagingTemplate;
    private final Clock clock;
    private final MovementCalculator movementCalculator;
    private final MovementMaker movementMaker;
    private final Telescope telescope;
    private final TargetDataSource targetDataSource;
 
    @Scheduled(fixedRate = 500)
    public void publishState() {
        Map<String, Object> state = new HashMap<>();
        
        state.put("clock", Map.of(
            "rpi", clock.getRpi(),
            "rounds", clock.getCurrentRounds()
        ));
        
        state.put("movementCalculator", Map.of(
            "idealPos", movementCalculator.getIdealPos()
        ));
        
        state.put("movementMaker", Map.of(
            "ca", movementMaker.getCa(),
            "pa", movementMaker.getPa(),
            "queueSize", movementMaker.getQueueSize()
        ));
        
        state.put("telescope", Map.of(
            "rotation", telescope.getRotation()
        ));
        
        state.put("targetDataSource", Map.of(
            "round", targetDataSource.getRound()
        ));
        
        state.put("timestamp", System.currentTimeMillis());
        
        messagingTemplate.convertAndSend("/topic/system-state", state);
    }
}