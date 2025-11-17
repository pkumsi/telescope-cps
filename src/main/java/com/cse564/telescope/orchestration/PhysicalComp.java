package com.cse564.telescope.orchestration;

import com.cse564.telescope.physical.TargetData;
import com.cse564.telescope.physical.Telescope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class PhysicalComp {
    
    private final TargetData targetData;
    private final Telescope telescope;
    

    public List<Float> getFutureTargetRots() {
        return targetData.getFutureTargetRots();
    }
    
    
    public float getCurrentRotRaw() {
        return telescope.getCurrentRotRaw();
    }
}