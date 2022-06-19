package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.entities.CategoryEntity;
import bg.softuni.battleships.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findAllByCategory(CategoryEntity category);

}
