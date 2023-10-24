package com.example.web;

import com.example.web.dataRepository.DataEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataEntityTest {

    @Test
    public void testSetCity() {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setCity("Karnataka");
        assertEquals("Karnataka", dataEntity.getCity());
    }


}

