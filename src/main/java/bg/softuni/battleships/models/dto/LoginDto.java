package bg.softuni.battleships.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDto {

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    private String username;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
