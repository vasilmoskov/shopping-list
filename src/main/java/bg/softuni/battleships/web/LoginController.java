package bg.softuni.battleships.web;

import bg.softuni.battleships.models.dto.LoginDto;
import bg.softuni.battleships.service.UserService;
import bg.softuni.battleships.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("login")
public class LoginController {
    private final UserService userService;
    private final CurrentUser currentUser;

    public LoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public String getLogin() {
        if (currentUser.isLoggedIn()) {
                return "redirect:/";
        }
        return "login";
    }

    @ModelAttribute("loginModel")
    public LoginDto loginModel() {
        return new LoginDto();
    }

    @PostMapping
    public String doLogin(@Valid LoginDto loginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginModel", loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginModel", bindingResult);
            return "redirect:login";
        }

        if (!this.userService.loginSuccessful(loginDto)) {
            redirectAttributes.addFlashAttribute("loginModel", loginDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";
        }

        return "redirect:/";
    }
}
