package bg.softuni.shoppinglist.models.dto;

import bg.softuni.shoppinglist.models.validation.NotOccupied;
import bg.softuni.shoppinglist.models.validation.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@PasswordsMatch(password = "password", confirmPassword = "confirmPassword")
public class RegisterDto {
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters!")
    @NotOccupied(message = "Username is already occupied!")
    private String username;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters!")
    private String password;

    @Size(min = 3, max = 20, message = "Confirm password must be between 3 and 20 characters!")
    private String confirmPassword;

    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    public String getUsername() {
        return username;
    }

    public RegisterDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
