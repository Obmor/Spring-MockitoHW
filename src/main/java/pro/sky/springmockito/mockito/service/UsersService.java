package pro.sky.springmockito.mockito.service;

import org.springframework.stereotype.Service;
import pro.sky.springmockito.mockito.dto.UsersDto;
import pro.sky.springmockito.mockito.mapper.UsersMapper;
import pro.sky.springmockito.mockito.model.Users;
import pro.sky.springmockito.mockito.repository.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public UsersDto createUser(UsersDto dto) {
        Users newUsers = usersMapper.toUsers(dto);
        return usersMapper.toDto(usersRepository.save(newUsers));
    }

    public UsersDto getById(Long id) {
        Users currentUser = usersRepository.findById(id).orElse(new Users(1, "test", "test@mail", "Ivan", "Ivanov"));
        return usersMapper.toDto(currentUser);
    }
}
