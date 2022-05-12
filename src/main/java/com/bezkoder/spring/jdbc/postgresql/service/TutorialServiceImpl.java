package com.bezkoder.spring.jdbc.postgresql.service;

import java.util.ArrayList;
import java.util.List;
import javax.cache.Caching;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
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

    @Override
    @CacheRemove(cacheName = "myCache1")
    public int deleteById(Long id) {

        int deleteId = tutorialRepository.deleteById(id);
        Caching.getCachingProvider().getCacheManager().destroyCache("myCache1");
        return deleteId;
    }
}
