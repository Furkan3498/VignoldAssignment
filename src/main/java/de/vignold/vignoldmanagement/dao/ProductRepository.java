package de.vignold.vignoldmanagement.dao;

import de.vignold.vignoldmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
