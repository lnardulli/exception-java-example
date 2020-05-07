package com.exception.demo.component;

import com.exception.demo.model.Young;
import com.exception.demo.model.YoungResponse;
import javassist.NotFoundException;

public interface YoungService {

    void createPersona(Young young);
    YoungResponse findByName(String name) throws NotFoundException;
}
