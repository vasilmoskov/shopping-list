package bg.softuni.battleships.web;

import bg.softuni.battleships.service.UserService;
import bg.softuni.battleships.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    private final UserService userService;
    private final CurrentUser currentUser;

    public LogoutController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/logout")
    public String logout() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }
        this.userService.logout();
        return "redirect:/";
    }
}