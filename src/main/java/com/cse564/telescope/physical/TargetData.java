package com.cse564.telescope.physical;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@Getter
public class TargetData {
    
    private List<Float> data = new ArrayList<>();
    private boolean cyclic = true;
    
    @PostConstruct
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("target-rotations.json");
            
            @SuppressWarnings("unchecked")
            Map<String, Object> config = mapper.readValue(resource.getInputStream(), Map.class);
            
            @SuppressWarnings("unchecked")
            List<Number> rotations = (List<Number>) config.get("rotations");
            
            data = new ArrayList<>();
            for (Number rotation : rotations) {
                data.add(rotation.floatValue());
            }
            
            cyclic = (Boolean) config.getOrDefault("cyclic", true);
            
            log.info("TargetData loaded: {} rotations, cyclic={}", data.size(), cyclic);
            log.info("Target rotations: {}", data);
            
        } catch (IOException e) {
            log.error("Failed to load target-rotations.json, using defaults", e);

            data = List.of(45.0f, 90.0f, 135.0f, 180.0f, 225.0f, 270.0f, 315.0f, 360.0f);
            cyclic = true;
        }
    }
    
    public List<Float> getFutureTargetRots() {
        log.debug("TargetData A1: Returning futureTargetRots with {} entries", data.size());
        return new ArrayList<>(data);
    }
}