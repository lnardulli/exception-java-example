package com.exception.demo.respository;

import com.exception.demo.model.Young;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YoungRepository extends CrudRepository<Young, Long> {

    List<Young> findByName(String name);
    Young save(Young young);
}
