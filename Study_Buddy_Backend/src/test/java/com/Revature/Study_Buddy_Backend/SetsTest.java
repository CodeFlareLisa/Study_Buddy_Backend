package com.Revature.Study_Buddy_Backend;

import com.Revature.Study_Buddy_Backend.Model.Sets;
import com.Revature.Study_Buddy_Backend.Repository.SetsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SetsTest {
    @Autowired
    private SetsRepo setsRepo;

    @Test
    void testAddSets(){
        Sets set = new Sets();
        set.setPrivacy("public");
        set.setUserId(1L);
        set.setSetName("Test set");
        setsRepo.save(set);
    }

    @Test
    void testGetSetsId(){
        Sets set = setsRepo.findBysetId(7L);
        assertNotNull(set);
        System.out.println(set);
    }

    @Test
    void testSetsList(){
        List<Sets> setsList = setsRepo.findAll();
        assertThat(setsList).size().isGreaterThan(0);
        System.out.println("Sets list: " + setsList.size());
    }

    @Test
    void testUpdateSets(){
        Sets sets = setsRepo.findBysetId(7L);
        sets.setSetName("test update");
        assertNotNull(sets,sets.toString());
        setsRepo.save(sets);
        System.out.println(sets);
    }

    @Test
    void testDeleteBySetId(){
        Sets sets = setsRepo.findBysetId(7L);
        assertNotNull(sets, sets.toString());
        setsRepo.delete(sets);
        System.out.println(sets);
    }
}
