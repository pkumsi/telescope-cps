package com.cse564.telescope.api;

import com.cse564.telescope.cyber.Clock;
import com.cse564.telescope.cyber.MovementCalculator;
import com.cse564.telescope.cyber.MovementMaker;
import com.cse564.telescope.physical.TargetData;
import com.cse564.telescope.physical.Telescope;
import com.cse564.telescope.sa.Motor;
import com.cse564.telescope.sa.TargetDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// REST API Controller 
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TelescopeController {
    
    private final Clock clock;
    private final MovementCalculator movementCalculator;
    private final MovementMaker movementMaker;
    private final TargetData targetData;
    private final Telescope telescope;
    private final TargetDataSource targetDataSource;
    private final Motor motor;
    
    //Get complete system status
    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        
        // Cyber components
        Map<String, Object> cyber = new HashMap<>();
        cyber.put("clock", Map.of(
            "rpi", clock.getRpi(),
            "rounds", clock.getCurrentRounds()
        ));
        cyber.put("movementCalculator", Map.of(
            "idealPos", movementCalculator.getIdealPos()
        ));
        cyber.put("movementMaker", Map.of(
            "ca", movementMaker.getCa(),
            "pa", movementMaker.getPa(),
            "queueSize", movementMaker.getQueueSize()
        ));
        status.put("cyber", cyber);
        
        // Physical components
        Map<String, Object> physical = new HashMap<>();
        physical.put("targetData", Map.of(
            "rotations", targetData.getData(),
            "cyclic", targetData.isCyclic()
        ));
        physical.put("telescope", Map.of(
            "rotation", telescope.getRotation()
        ));
        status.put("physical", physical);
        
        // Sensors & Actuators
        Map<String, Object> sa = new HashMap<>();
        sa.put("targetDataSource", Map.of(
            "round", targetDataSource.getRound()
        ));
        sa.put("motor", Map.of(
            "error", motor.getError()
        ));
        status.put("sensorsActuators", sa);
        
        return status;
    }
    
    //Get Clock state
    @GetMapping("/clock")
    public Map<String, Object> getClock() {
        return Map.of(
            "rpi", clock.getRpi(),
            "rounds", clock.getCurrentRounds()
        );
    }
    
    // Get Telescope state
    @GetMapping("/telescope")
    public Map<String, Object> getTelescope() {
        return Map.of(
            "rotation", telescope.getRotation(),
            "currentRotRaw", telescope.getCurrentRotRaw()
        );
    }
    
    //Get MovementMaker state

    @GetMapping("/movement-maker")
    public Map<String, Object> getMovementMaker() {
        return Map.of(
            "ca", movementMaker.getCa(),
            "pa", movementMaker.getPa(),
            "queueSize", movementMaker.getQueueSize()
        );
    }
    
    // Get Target Data
    @GetMapping("/target-data")
    public Map<String, Object> getTargetData() {
        return Map.of(
            "rotations", targetData.getData(),
            "cyclic", targetData.isCyclic(),
            "currentRound", targetDataSource.getRound()
        );
    }
}