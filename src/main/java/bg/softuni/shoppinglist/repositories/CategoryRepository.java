package bg.softuni.shoppinglist.repositories;

import bg.softuni.shoppinglist.models.entities.CategoryEntity;
import bg.softuni.shoppinglist.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryEnum name);
}
