package by.shumilov.clevertec.controller;

import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.dao.data_from_db.ItemDAOCreator;
import by.shumilov.clevertec.dao.data_from_db.ProductDAOFromDB;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDAOFromDB dao = new ItemDAOCreator().getProductFromDBDao();

    @GetMapping()
    public List<Product> gelAllProducts() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id) {
        return dao.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Void> createUser(@RequestBody Product product) {
        dao.save(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", dao.findById(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Product product, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "products/edit";

        dao.update(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        dao.delete(id);
    }
}
