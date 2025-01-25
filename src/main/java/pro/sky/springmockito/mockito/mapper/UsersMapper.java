package pro.sky.springmockito.mockito.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pro.sky.springmockito.mockito.dto.UsersDto;
import pro.sky.springmockito.mockito.model.Users;

@Mapper(componentModel = "Spring")
public interface UsersMapper {

    @Mapping(target = "id", ignore = true)
    Users toUsers(UsersDto dto);

    UsersDto toDto(Users users);
}
