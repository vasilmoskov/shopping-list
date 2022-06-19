package bg.softuni.battleships.web;

import bg.softuni.battleships.models.dto.ProductDto;
import bg.softuni.battleships.service.ProductService;
import bg.softuni.battleships.utils.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public ProductController(CurrentUser currentUser, ProductService shipService) {
        this.currentUser = currentUser;
        this.productService = shipService;
    }

    @GetMapping("/products/add")
    public String addProduct() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/login";
        }
        return "product-add";
    }

    @ModelAttribute("productDto")
    public ProductDto productDto() {
        return new ProductDto();
    }

    @PostMapping("/products/add")
    public String addProduct(@Valid ProductDto productDto, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productDto", productDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.productDto", bindingResult);
            return "redirect:add";
        }

        productService.add(productDto);

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyOne(@PathVariable long id) {
        this.productService.buyOne(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll() {
        this.productService.buyAll();
        return "redirect:/";
    }
}
