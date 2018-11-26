package com.luxoft.logeek.loop.service.impl;

import com.luxoft.logeek.loop.dto.UserDto;
import com.luxoft.logeek.loop.entity.UserEntity;
import com.luxoft.logeek.loop.repository.UserRepository;
import com.luxoft.logeek.loop.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public Set<UserEntity> findInLoop(List<UserDto> userDtos) {
    return userDtos.stream()
            .map(UserDto::getUserId)
            .map(userRepository::findById)
            .map(Optional::get)
            .collect(Collectors.toSet());
  }

  @Override
  public List<UserEntity> findWithSingleCall(List<UserDto> userDtos) {
    List<Long> ids = userDtos.stream()
            .map(UserDto::getUserId)
            .collect(Collectors.toList());

    return userRepository.findAllById(ids);
  }
}
