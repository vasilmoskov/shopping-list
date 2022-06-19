package bg.softuni.battleships.service.impl;

import bg.softuni.battleships.models.enums.CategoryEnum;
import bg.softuni.battleships.models.dto.ProductDto;
import bg.softuni.battleships.models.entities.CategoryEntity;
import bg.softuni.battleships.models.entities.ProductEntity;
import bg.softuni.battleships.repositories.CategoryRepository;
import bg.softuni.battleships.repositories.ProductRepository;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.service.ProductService;
import bg.softuni.battleships.utils.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository shipRepository, CategoryRepository categoryRepository,
                              ModelMapper modelMapper) {
        this.productRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ProductDto productDto) {
        CategoryEntity categoryEntity = categoryRepository.findByName(productDto.getCategory()).orElseThrow();

        ProductEntity productEntity = this.modelMapper.map(productDto, ProductEntity.class)
                .setCategory(categoryEntity);

        this.productRepository.save(productEntity);
    }

    @Override
    public List<ProductDto> getAllFoods() {
        return getAllByCategory(CategoryEnum.FOOD);
    }

    @Override
    public List<ProductDto> getAllDrinks() {
        return getAllByCategory(CategoryEnum.DRINK);
    }

    @Override
    public List<ProductDto> getAllHouseholds() {
        return getAllByCategory(CategoryEnum.HOUSEHOLD);
    }

    @Override
    public List<ProductDto> getOthers() {
        return getAllByCategory(CategoryEnum.OTHER);
    }

    @Override
    public BigDecimal getPriceOfAllProducts() {
        return this.productRepository.findAll().stream()
                .map(ProductEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void buyOne(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        this.productRepository.deleteAll();
    }

    private List<ProductDto> getAllByCategory(CategoryEnum category) {
        CategoryEntity categoryEntity = this.categoryRepository.findByName(category).orElseThrow();

        return this.productRepository.findAllByCategory(categoryEntity)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ProductDto toDto(ProductEntity entity) {
        return this.modelMapper.map(entity, ProductDto.class)
                .setCategory(entity.getCategory().getName());
    }

}
