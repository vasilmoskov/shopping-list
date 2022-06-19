package bg.softuni.battleships.models.dto;

import bg.softuni.battleships.models.enums.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDto {
    private long id;

    @Size(min = 2, max = 20, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @Size(min = 5, message = "Description length must be more than 5 characters!")
    private String description;

    @Positive(message = "Price must be a positive number!")
    private BigDecimal price;

    @FutureOrPresent(message = "The date cannot be in the past")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime neededBefore;

    @NotNull(message = "Please select a category!")
    private CategoryEnum category;

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ProductDto setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductDto setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public long getId() {
        return id;
    }

    public ProductDto setId(long id) {
        this.id = id;
        return this;
    }
}
