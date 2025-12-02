package com.cse564.telescope.sa;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TargetDataSourceTest {

    @Test
    void cyclesThroughRotationsAndAccumulatesRounds() {
        TargetDataSource targetDataSource = new TargetDataSource();
        List<Float> rotations = List.of(10.0f, 20.0f);

        float first = targetDataSource.getNextIntervalRot(rotations);
        assertEquals(10.0f, first);

        targetDataSource.updateRound(5);
        assertEquals(5.0f, targetDataSource.getRound());
        assertEquals(1, targetDataSource.getIntervalIndex());

        float second = targetDataSource.getNextIntervalRot(rotations);
        assertEquals(20.0f, second);

        targetDataSource.updateRound(5);
        assertEquals(10.0f, targetDataSource.getRound());
        assertEquals(2, targetDataSource.getIntervalIndex());

        float third = targetDataSource.getNextIntervalRot(rotations);
        assertEquals(10.0f, third);
    }
}
