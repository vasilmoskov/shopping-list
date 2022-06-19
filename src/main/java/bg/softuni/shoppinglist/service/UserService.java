package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.models.dto.LoginDto;
import bg.softuni.shoppinglist.models.dto.RegisterDto;
import bg.softuni.shoppinglist.models.entities.UserEntity;

public interface UserService {
    void register(RegisterDto registerDto);

    UserEntity getUser(String username);

    boolean loginSuccessful(LoginDto loginDto);

    void logout();
}
