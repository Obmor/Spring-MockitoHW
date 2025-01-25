package pro.sky.springmockito.mockito.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.springmockito.mockito.model.Users;
import pro.sky.springmockito.mockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RequestMapping("/department")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/sum")
    public Integer getSumOfSalaries(@PathVariable("id") int department) {
        return departmentService.getSumOfSalaries(department);
    }

    @GetMapping("/{id}/salary/max")
    public Integer getMaxSalary(@PathVariable("id") int department) {
        return departmentService.getMaxSalary(department);
    }

    @GetMapping("/salary/min")
    public Integer getMinSalary(@RequestParam("departmentId") int department) {
        return departmentService.getMinSalary(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Users>> getEmployeesGroupedByDepartment() {
        return departmentService.getEmployeesGroupedByDepartment();
    }
}

