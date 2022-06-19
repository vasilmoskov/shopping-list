package bg.softuni.shoppinglist.repositories;

import bg.softuni.shoppinglist.models.entities.CategoryEntity;
import bg.softuni.shoppinglist.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findAllByCategory(CategoryEntity category);

}
