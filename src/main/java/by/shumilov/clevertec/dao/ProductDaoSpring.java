package by.shumilov.clevertec.dao;

import by.shumilov.clevertec.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDaoSpring extends JpaRepository<Product, Integer> {
}
