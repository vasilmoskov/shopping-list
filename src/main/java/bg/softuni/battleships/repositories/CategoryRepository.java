package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.entities.CategoryEntity;
import bg.softuni.battleships.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryEnum name);
}
