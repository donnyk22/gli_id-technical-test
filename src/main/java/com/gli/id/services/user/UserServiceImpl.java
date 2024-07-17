package com.gli.id.services.user;

import com.gli.id.dtos.UserDto;
import com.gli.id.dtos.exceptions.BusinessException;
import com.gli.id.dtos.mappers.UserMapper;
import com.gli.id.models.User;
import com.gli.id.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        Iterable<User> users = userRepository.findAll();
        if (!users.iterator().hasNext()) throw new BusinessException("User is empty");
        return StreamSupport
                .stream(users.spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
