package pro.sky.springmockito.mockito.service;

import org.example.exception.EmployeeAlreadyAddedException;
import org.example.exception.EmployeeNotFoundException;
import org.example.exception.EmployeeStoragelsFullException;
import org.example.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentServiceTest {
    private final UsersService usersService = new UsersService();


    @BeforeEach
    public void beforeEach() {
        usersService.add(1, "test", "test@mail", "Ivan", "Ivanov", 123, 10_000);
    }

    @Test
    void addPositiveTest() {
        Users expected = new Users(1, "test", "test@mail", "Ivan",
                "Ivanov", 123, 10_000);

        usersService.add(1, "test", "test@mail", "Ivan",
                "Ivanov", 123, 10_000);
        assertThatNoException().isThrownBy(() -> usersService.find("Ivan", "Ivanov"));
        assertThat(usersService.find("Ivan", "Ivanov")).isEqualTo(expected);
        assertThat(usersService.findAll()).contains(expected);
    }


    @Test
    void addNegative2Test() {
        Users expected = new Users(1, "test", "test@mail", "Ivan",
                "Ivanov", 123, 10_000);
        assertThat(usersService.findAll()).contains(expected);

        assertThatExceptionOfType(UsersAlreadyAddedException.class)
                .isThrownBy(() -> usersService.add(1, "test", "test@mail", "Ivan",
                        "Ivanov", 123, 10_000));
    }

    @Test
    void removePositiveTest() {
        Users expected = new Users(1, "test", "test@mail", "Ivan",
                "Ivanov", 123, 10_000);
        assertThat(usersService.findAll()).contains(expected);

        usersService.remove("Ivan", "Ivanov");

        assertThat(usersService.findAll()).doesNotContain(expected);
        assertThatExceptionOfType(UsersNotFoundException.class)
                .isThrownBy(() -> usersService.find("Ivan", "Ivanov"));
    }

    @Test
    void removeNegativeTest() {
        Users expected = new Users(1, "test", "test@mail", "Ivan",
                "Ivanov", 123, 10_000);
        assertThat(usersService.findAll()).doesNotContain(expected);
        assertThatExceptionOfType(UsersNotFoundException.class).isThrownBy(() -> usersService.remove("Ivan", "Ivanov"));
    }

    @Test
    void findPositiveTest() {
        Users expected = new Users(1, "test", "test@mail", "Ivan",
                "Ivanov", 123, 10_000);
        assertThat(usersService.findAll()).contains(expected);
        assertThat(usersService.find("Ivan", "Ivanov")).isEqualTo(expected);
    }

    @Test
    void findNegativeTest() {
        Users employee = new Users(1, "test", "test@mail", "Ivan",
                "Ivanov", 123, 10_000);
        assertThat(usersService.findAll()).doesNotContain(employee);
        assertThatExceptionOfType(UsersNotFoundException.class).isThrownBy(() -> usersService.find("Ivan", "Ivanov"));
    }

    @Test
    void findAll() {
        assertThat(usersService.findAll()).containsExactlyInAnyOrder(
                new Users(1, "test", "test@mail", "Ivan",
                        "Ivanov", 123, 10_000)
        );
    }
}
