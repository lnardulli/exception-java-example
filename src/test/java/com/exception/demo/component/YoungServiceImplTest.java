package com.exception.demo.component;

import com.exception.demo.model.Young;
import com.exception.demo.model.YoungResponse;
import com.exception.demo.model.exceptions.YoungAgeException;
import com.exception.demo.respository.YoungRepository;
import com.exception.demo.time.Time;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class YoungServiceImplTest {

    private YoungService youngService;

    @Mock
    private YoungRepository youngFakeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        youngService = new YoungServiceImpl(youngFakeRepository, new Time());
    }

    @Test
    public void givenPersonUnderAgeOfYoungWhenCreatePersonThenShowException() throws YoungAgeException {
        final Integer UNDER_AGE = 12;
        assertThrows(YoungAgeException.class, () -> {
            youngService.createPersona(Young.builder().age(UNDER_AGE).name("Bill").build());
        });
    }

    @Test
    public void givenPersonUpperAgeOfYoungWhenCreatePersonThenShowException() throws YoungAgeException {
        final Integer UPPER_AGE = 22;

        Exception exception = assertThrows(
                YoungAgeException.class,
                () -> youngService.createPersona(Young.builder().age(UPPER_AGE).name("Charly").build()));

        assertEquals("Age cannot exceed 21", exception.getMessage());
        assertTrue(exception.getMessage().contains("Age cannot exceed 21"));

    }

    @Test
    void givenPersonWhenCreatePersonThenShowCorrectYoungPerson() {
        Young youngPerson = new Young(22L, "Kenny", 18);

        //doNothing().doThrow().when(youngFakeRepository).save(isA(Young.class));
        youngService.createPersona(youngPerson);
    }

    @Test
    void givenNameWhenFindPersonThenShowPerson() throws NotFoundException {
        final String name = "Kenny";
        final Young youngPerson = new Young(22L, name, 18);

        final List<Young> youngList = Arrays.asList(youngPerson);

        given(youngFakeRepository.findByName(name)).willReturn(youngList);
        // Other way to mock the repository
        //when(youngFakeRepository.findByName(isA(String.class))).thenReturn(youngList);

        YoungResponse yr = youngService.findByName("Kenny");
        assertResponse(name, yr);
    }

    @Test
    void givenNameWhenFindPersonThenNotHaveResult() {
        Exception exception = assertThrows(
                NoResultException.class,
                () -> youngService.findByName("Kenny"));

        assertEquals("Not result with this name", exception.getMessage());

    }

    private void assertResponse(String name, YoungResponse yr) {
        assertThat(yr.getYoung().get(0).getName()).isEqualTo(name);
        assertThat(yr.getStatusCode()).isEqualTo(202);
        assertThat(yr.getYoung().size()).isEqualTo(1);
        assertThat(yr.getYoung().get(0).getName()).isEqualTo(name);
    }

}