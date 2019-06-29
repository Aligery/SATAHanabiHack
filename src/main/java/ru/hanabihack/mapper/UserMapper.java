package ru.hanabihack.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import ru.hanabihack.DTO.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
}
