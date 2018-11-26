package com.luxoft.logeek.loop.service;

import com.luxoft.logeek.loop.dto.UserDto;
import com.luxoft.logeek.loop.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

	Set<UserEntity> findInLoop(List<UserDto> userDtos);

	List<UserEntity> findWithSingleCall(List<UserDto> userDtos);
}