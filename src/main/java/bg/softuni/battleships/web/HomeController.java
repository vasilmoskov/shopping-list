package bg.softuni.battleships.web;

import bg.softuni.battleships.models.dto.ProductDto;
import bg.softuni.battleships.service.ProductService;
import bg.softuni.battleships.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CurrentUser currentUser;

    public HomeController(ProductService shipService, CurrentUser currentUser) {
        this.productService = shipService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("productDto")
    public ProductDto productDto() {
        return new ProductDto();
    }

    @GetMapping("/")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()) {
            return "index";
        }

        model.addAttribute("foods", productService.getAllFoods());
        model.addAttribute("drinks", productService.getAllDrinks());
        model.addAttribute("households", productService.getAllHouseholds());
        model.addAttribute("other", productService.getOthers());
        model.addAttribute("totalPrice", productService.getPriceOfAllProducts());

        return "home";
    }
}
