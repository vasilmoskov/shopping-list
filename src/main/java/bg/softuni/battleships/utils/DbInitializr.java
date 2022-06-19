package bg.softuni.battleships.utils;

import bg.softuni.battleships.models.entities.CategoryEntity;
import bg.softuni.battleships.models.enums.CategoryEnum;
import bg.softuni.battleships.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInitializr implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public DbInitializr(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() > 0) {
            return;
        }

        CategoryEntity food = new CategoryEntity()
                .setName(CategoryEnum.FOOD);

        CategoryEntity drink = new CategoryEntity()
                .setName(CategoryEnum.DRINK);

        CategoryEntity household = new CategoryEntity()
                .setName(CategoryEnum.HOUSEHOLD);

        CategoryEntity other = new CategoryEntity()
                .setName(CategoryEnum.OTHER);

        this.categoryRepository.saveAll(List.of(food, drink, household, other));
    }
}
