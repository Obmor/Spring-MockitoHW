package pro.sky.springmockito.mockito.service;

import org.springframework.stereotype.Service;
import pro.sky.springmockito.mockito.dto.UsersDto;
import pro.sky.springmockito.mockito.mapper.UsersMapper;
import pro.sky.springmockito.mockito.model.Users;
import pro.sky.springmockito.mockito.repository.UsersRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final Map<String, Users> users = new HashMap<>();

    public UsersService() {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public UsersDto createUser(UsersDto dto) {
        Users newUsers = usersMapper.toUsers(dto);
        return usersMapper.toDto(usersRepository.save(newUsers));
    }

    public UsersDto getById(Long id) {
        Users currentUser = usersRepository.findById(id).orElse(new Users(1, "test", "test@mail",
                "Ivan", "Ivanov", 123, 10_000));
        return usersMapper.toDto(currentUser);
    }

    public Collection<Users> findAll() {
        return Collections.unmodifiableCollection(users.values());
    }
}
