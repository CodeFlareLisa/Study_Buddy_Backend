package com.Revature.Study_Buddy_Backend.Repository;

import com.Revature.Study_Buddy_Backend.Model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepo extends JpaRepository <Notes, Long> {
    List<Notes> findByUserId(Long setId);
    List<Notes> findBySetId(Long setId);
    Notes findBynotesId(Long notesId);
    List<Notes> findBySetIdAndUserId(Long setId, Long userId);

}