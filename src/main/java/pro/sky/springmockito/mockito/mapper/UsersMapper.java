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

//    public Users toUsers(UsersDto dto) {
//        return Users.builder()
//                .email(dto.getEmail())
//                .userName(dto.getUserName())
//                .firstName(dto.getFirstName())
//                .lastName(dto.getLastName())
//                .build();
//    }
//
//    public UsersDto toDto(Users users) {
//        return UsersDto.builder()
//                .id(users.getId())
//                .email(users.getEmail())
//                .userName(users.getUserName())
//                .firstName(users.getFirstName())
//                .lastName(users.getLastName())
//                .build();
//    }
}
