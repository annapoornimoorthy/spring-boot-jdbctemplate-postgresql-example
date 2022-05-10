package com.bezkoder.spring.jdbc.postgresql.service;

import java.util.ArrayList;
import java.util.List;
import javax.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bezkoder.spring.jdbc.postgresql.model.Tutorial;
import com.bezkoder.spring.jdbc.postgresql.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Override
    @CacheResult(cacheName = "myCache1")
    public List<Tutorial> getTutorial() {
        return tutorialRepository.findAll();
//        Tutorial t = new Tutorial(1, "t", "t", false);
//        List<Tutorial> listTutorial =new ArrayList<>();
//        listTutorial.add(t);
//        return listTutorial;
    }
}
