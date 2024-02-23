package com.crio.codingame.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;

public class ContestRepository implements IContestRepository {

    private final Map<String,Contest> contestMap;
    private Integer autoIncrement = 0;

    
    
    public ContestRepository() {
        contestMap = new HashMap<String,Contest>();
    }

    public ContestRepository(Map<String, Contest> contestMap) {
        this.contestMap = contestMap;
        this.autoIncrement = contestMap.size();
    }

    @Override
    public Contest save(Contest entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Contest c = new Contest(Integer.toString(autoIncrement),entity.getName(),entity.getQuestions(),entity.getLevel(),entity.getCreator(),entity.getContestStatus());
            contestMap.put(c.getId(),c);
            return c;
        }
        contestMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Contest Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<Contest> findAll() {
        if(contestMap.isEmpty()) return new ArrayList<>();

        return contestMap.entrySet().stream()
                                    .map(e -> e.getValue())
                                    .collect(Collectors.toList());
    }

    @Override
    public Optional<Contest> findById(String id) {
        return Optional.ofNullable(contestMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return contestMap.containsKey(id);
    }

    @Override
    public void delete(Contest entity) {
        for(Map.Entry<String, Contest> entry: contestMap.entrySet()) {
            if(entry.getValue().getId() == entity.getId()) {
                contestMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        contestMap.remove(id);
    }

    @Override
    public long count() {
        return contestMap.size();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Contest Present in the Repository provided Level
    // Tip:- Use Java Streams

    @Override
    public List<Contest> findAllContestLevelWise(Level level) {
        if(contestMap.isEmpty()) return new ArrayList<>();

        return contestMap.entrySet().stream()
                                    .map(e -> e.getValue())
                                    .filter(e -> e.getLevel() == level)
                                    .collect(Collectors.toList());
        // List<Contest> contests = new ArrayList<>();
        // for (Entry<String, Contest> entry: contestMap.entrySet()) {
        //     if(entry.getValue().getLevel() == level) {
        //         contests.add(entry.getValue());
        //     }
        // }
        // return contests;
    }

    
}
