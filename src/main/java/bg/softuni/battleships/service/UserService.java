package bg.softuni.battleships.service;

import bg.softuni.battleships.models.dto.LoginDto;
import bg.softuni.battleships.models.dto.RegisterDto;
import bg.softuni.battleships.models.entities.UserEntity;

public interface UserService {
    void register(RegisterDto registerDto);

    UserEntity getUser(String username);

    boolean loginSuccessful(LoginDto loginDto);

    void logout();
}
