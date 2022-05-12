package com.bezkoder.spring.jdbc.postgresql.service;

import java.util.List;
import com.bezkoder.spring.jdbc.postgresql.model.Tutorial;

public interface TutorialService {
    public List<Tutorial> getTutorial();
    public int deleteById(Long id);
}
