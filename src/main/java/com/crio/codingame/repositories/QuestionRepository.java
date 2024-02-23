package com.crio.codingame.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;

public class QuestionRepository implements IQuestionRepository {

    private final Map<String, Question> questionMap;
    private Integer autoIncrement = 0;

    public QuestionRepository(){
        questionMap = new HashMap<String,Question>();
    }

    public QuestionRepository(Map<String, Question> questionMap) {
        this.questionMap = questionMap;
        this.autoIncrement = questionMap.size();
    }

    @Override
    public Question save(Question entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Question q = new Question(Integer.toString(autoIncrement),entity.getTitle(),entity.getLevel(),entity.getScore());
            questionMap.put(q.getId(),q);
            return q;
        }
        questionMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Question Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<Question> findAll() {
        if(questionMap.isEmpty()) return new ArrayList<>();

        return questionMap.entrySet().stream()
                                        .map(e -> e.getValue())
                                        .collect(Collectors.toList());
    }

    @Override
    public Optional<Question> findById(String id) {
        return Optional.ofNullable(questionMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return questionMap.containsKey(id);
    }

    @Override
    public void delete(Question entity) {
        for(Map.Entry<String, Question> entry: questionMap.entrySet()) {
            if(entry.getValue().getId() == entity.getId()) {
                questionMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public void deleteById(String id) {
        questionMap.remove(id);
    }

    @Override
    public long count() {
        return questionMap.size();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Question Present in the Repository provided Level
    // Tip:- Use Java Streams

    @Override
    public List<Question> findAllQuestionLevelWise(Level level) {
        if(questionMap.isEmpty()) return new ArrayList<>();

        return questionMap.entrySet().stream()
                                        .map(e -> e.getValue())
                                        .filter(e -> e.getLevel() == level)
                                        .collect(Collectors.toList());
    }
    
}
