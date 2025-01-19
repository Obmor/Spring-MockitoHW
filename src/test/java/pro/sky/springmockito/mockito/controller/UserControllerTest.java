package pro.sky.springmockito.mockito.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pro.sky.springmockito.mockito.dto.UsersDto;
import pro.sky.springmockito.mockito.service.UsersService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @Test
    void testSuccessfulGetById() {
        UsersDto dto = new UsersDto(1, "test", "test@mail", "Ivan", "Ivanov");

        when(usersService.getById(1L)).thenReturn(dto);

        ResponseEntity<UsersDto> response = usersController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto.getUserName(), Objects.requireNonNull(response.getBody()).getUserName());
        assertEquals(dto.getEmail(), response.getBody().getEmail());
    }

}
