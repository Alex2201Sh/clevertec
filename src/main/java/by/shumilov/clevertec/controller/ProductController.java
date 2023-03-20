package by.shumilov.clevertec.controller;

import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.dao.ProductDaoSpring;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDaoSpring productDaoSpring;

    @Autowired
    public ProductController(ProductDaoSpring productDaoSpring) {
        this.productDaoSpring = productDaoSpring;
        init();
    }

    private void init() {
        productDaoSpring.save(Product.builder().setId(0).setName("first").setPrice(0.00).setPromotion(false).build());
    }

    @GetMapping()
    public List<Product> gelAllProducts() {
        return productDaoSpring.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productDaoSpring.save(product);
    }

    @PatchMapping("/{id}")
    public Product update(@PathVariable("id") Product productFromDb,
                          @RequestBody Product product) {
        BeanUtils.copyProperties(product, productFromDb, "id");
        return productDaoSpring.save(productFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Product product) {
        productDaoSpring.delete(product);
    }
}
