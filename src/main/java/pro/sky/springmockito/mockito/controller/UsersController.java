package pro.sky.springmockito.mockito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.springmockito.mockito.dto.UsersDto;
import pro.sky.springmockito.mockito.service.UsersService;

@RestController

@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usersService.getById(id));
    }
}
