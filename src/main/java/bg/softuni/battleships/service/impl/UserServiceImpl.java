package bg.softuni.battleships.service.impl;

import bg.softuni.battleships.models.dto.LoginDto;
import bg.softuni.battleships.models.dto.RegisterDto;
import bg.softuni.battleships.models.entities.UserEntity;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.service.UserService;
import bg.softuni.battleships.utils.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void register(RegisterDto registerDto) {
        UserEntity userEntity = this.modelMapper.map(registerDto, UserEntity.class)
                .setPassword(passwordEncoder.encode(registerDto.getPassword()));

        this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUser(String username) {
        return this.userRepository.findByUsername(username)
                .orElse(null);
    }

    @Override
    public boolean loginSuccessful(LoginDto loginDto) {
        UserEntity user = this.userRepository.findByUsername(loginDto.getUsername()).orElse(null);

        if (user == null) {
            return false;
        }

        boolean correctPassword = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());

        if (!correctPassword) {
            return false;
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setLoggedIn(true);

        return true;
    }

    @Override
    public void logout() {
        currentUser.clear();
    }
}
