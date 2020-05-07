package com.exception.demo.component;

import com.exception.demo.model.Young;
import com.exception.demo.model.YoungResponse;
import com.exception.demo.model.exceptions.YoungAgeException;
import com.exception.demo.respository.YoungRepository;
import com.exception.demo.time.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class YoungServiceImpl implements YoungService {
    public final static Integer MINIMUM_ACCEPTED_AGE = 16;
    public final static Integer MAXIMUM_ACCEPTED_AGE = 21;

    private Time timestamp;

    private YoungRepository youngRepository;

    @Autowired
    public YoungServiceImpl(YoungRepository youngRepository, Time timestamp) {
        this.youngRepository = youngRepository;
        this.timestamp = timestamp;
    }

    @Override
    public void createPersona(Young young) {

        if (young.getAge() < MINIMUM_ACCEPTED_AGE) {
            throw YoungAgeException.of(young);
        } else if (young.getAge() > MAXIMUM_ACCEPTED_AGE) {
            /* * Error with specific message * */
            throw new YoungAgeException("Age cannot exceed " + MAXIMUM_ACCEPTED_AGE);
        }

        this.youngRepository.save(young);
    }

    @Override
    public YoungResponse findByName(String name) {

        List<Young> result = this.youngRepository.findByName(name);
        if (result.size() > 0) {
            return new YoungResponse(timestamp.getTime(), "Sucess", HttpStatus.ACCEPTED.value(), result);
        }

        throw new NoResultException("Not result with this name");
    }
}
