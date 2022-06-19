package bg.softuni.battleships.web;

import bg.softuni.battleships.models.dto.RegisterDto;
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
@RequestMapping("register")
public class RegisterController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public RegisterController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping
    public String getRegister() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @ModelAttribute("registerModel")
    public RegisterDto registerModel() {
        return new RegisterDto();
    }

    @PostMapping
    public String doLogin(@Valid RegisterDto registerDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerModel", registerDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerModel", bindingResult);
            return "redirect:register";
        }

        this.userService.register(registerDto);

        return "index";
    }
}
