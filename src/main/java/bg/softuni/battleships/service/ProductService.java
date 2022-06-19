package bg.softuni.battleships.service;

import bg.softuni.battleships.models.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void add(ProductDto shipDto);

    List<ProductDto> getAllFoods();

    List<ProductDto> getAllDrinks();

    List<ProductDto> getAllHouseholds();

    List<ProductDto> getOthers();

    BigDecimal getPriceOfAllProducts();

    void buyOne(long id);

    void buyAll();
}
