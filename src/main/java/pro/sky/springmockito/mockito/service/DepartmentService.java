package pro.sky.springmockito.mockito.service;

import org.springframework.stereotype.Service;
import pro.sky.springmockito.mockito.model.Users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final UsersService usersService;

    public DepartmentService(UsersService usersService) {
        this.usersService = usersService;
    }

    public Integer getMaxSalary(int department) {
        return usersService.findAll().stream()
                .filter(users -> users.getDepartment() == department)
                .map(Users::getSalary)
                .max(Integer::compareTo)
                .orElse(null);
    }

    public Integer getMinSalary(int department) {
        return usersService.findAll().stream()
                .filter(users -> users.getDepartment() == department)
                .map(Users::getSalary)
                .min(Integer::compareTo)
                .orElse(null);
    }

    public Integer getSumOfSalaries(int department) {
        return usersService.findAll().stream()
                .filter(users -> users.getDepartment() == department)
                .mapToInt(Users::getSalary)
                .sum();
    }

    public List<Users> getEmployeesFromDepartment(int department) {
        return usersService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Users>> getEmployeesGroupedByDepartment() {
        return usersService.findAll().stream()
                .collect(Collectors.groupingBy(Users::getDepartment));
    }
}
