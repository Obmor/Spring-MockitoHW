package pro.sky.springmockito.mockito.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.springmockito.mockito.dto.UsersDto;
import pro.sky.springmockito.mockito.mapper.UsersMapper;
import pro.sky.springmockito.mockito.model.Users;
import pro.sky.springmockito.mockito.repository.UsersRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UsersMapper usersMapper;

    @InjectMocks
    private UsersService usersService;

    @Test
    void testSuccessfulCreateTest() {

        UsersDto dto = new UsersDto(1, "test", "test@mail", "Ivan", "Ivanov", 123, 10_000);
        Users newUser = new Users(1, "test", "test@mail", "Ivan", "Ivanov", 123, 10_000);
        Users currentUser = new Users(2, "test", "test@mail", "Ivan", "Ivanov", 123, 10_000);
        UsersDto currentUserDto = new UsersDto(2, "test", "test@mail", "Ivan", "Ivanov", 123, 10_000);

        when(usersMapper.toUsers(dto)).thenReturn(newUser);
        when(usersRepository.save(newUser)).thenReturn(currentUser);
        when(usersMapper.toDto(currentUser)).thenReturn(currentUserDto);

        UsersDto dto1 = usersService.createUser(dto);

        assertEquals(newUser.getUserName(), dto1.getUserName());
        assertEquals(newUser.getEmail(), dto1.getEmail());
        verify(usersRepository).save(newUser);
    }

    @Test
    void testGetUserById() {

        Users testUser = new Users(1, "test", "test@mail", "Ivan", "Ivanov", 123, 10_000);
        UsersDto currentUserDto = new UsersDto(2, "test", "test@mail", "Ivan", "Ivanov", 123, 10_000);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(usersMapper.toDto(testUser)).thenReturn(currentUserDto);

        UsersDto actualUsers = usersService.getById(1L);

        assertEquals(testUser.getUserName(), actualUsers.getUserName());
        Assertions.assertEquals(testUser.getEmail(), testUser.getEmail());
        verify(usersRepository).findById(1L);
    }
}
