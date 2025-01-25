package pro.sky.springmockito.mockito.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.springmockito.mockito.model.Users;
import pro.sky.springmockito.mockito.service.DepartmentService;
import pro.sky.springmockito.mockito.service.UsersService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    private final Collection<Users> employees = List.of(
            new Users(1, "test", "test@mail",
                    "Ivan", "Ivanov", 123, 10_000)
    );

    @Mock
    private UsersService usersService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        when(usersService.findAll()).thenReturn(employees);
    }

    @Test
    public void getMaxSalaryPositiveTest() {
        int expected = 40000;
        assertThat(departmentService.getMaxSalary(1)).isEqualTo(expected);
    }

    @Test
    public void getMaxSalaryNegativeTest() {
        assertThat(departmentService.getMaxSalary(4)).isNull();
    }

    @Test
    public void getMinSalaryPositiveTest() {
        int expected = 30000;
        assertThat(departmentService.getMinSalary(2)).isEqualTo(expected);
    }

    @Test
    public void getMinSalaryNegativeTest() {
        assertThat(departmentService.getMinSalary(4)).isNull();
    }

    @Test
    public void getSumOfSalaryPositiveTest() {
        int expected = 70000;
        assertThat(departmentService.getSumOfSalaries(1)).isEqualTo(expected);
    }

    @Test
    public void getSumOfSalaryNegativeTest() {
        assertThat(departmentService.getSumOfSalaries(4)).isEqualTo(0);
    }

    @Test
    public void getEmployeesFromDepartmentTest() {
        assertThat(departmentService.getEmployeesFromDepartment(3)).contains(
                new Users(1, "test", "test@mail",
                        "Ivan", "Ivanov", 123, 10_000)
        );
    }

    @Test
    public void getEmployeesGroupedByDepartmentTest() {
        assertThat(departmentService.getEmployeesGroupedByDepartment()).containsExactlyInAnyOrderEntriesOf(
                Map.of(
                        1, List.of(
                                new Users(1, "test", "test@mail",
                                        "Ivan", "Ivanov", 123, 10_000)
                        )
                )
        );
    }
}
