package by.shumilov.clevertec.controller;

import by.shumilov.clevertec.bean.Product;
import by.shumilov.clevertec.dao.data_from_db.ItemDAOCreator;
import by.shumilov.clevertec.dao.data_from_db.ProductDAOFromDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductDAOFromDB dao = new ItemDAOCreator().getProductFromDBDao();

    public static void main(String[] args) {
        ProductController controller = new ProductController();
        Product newProduct = Product.builder().setName("nedwadaww product").setPrice(9.99).setPromotion(true).build();
        controller.dao.delete(41);
        controller.dao.findAll().forEach(System.out::println);
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", dao.findAll());
        return "products/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", dao.findById(id));
        return "products/show";
    }

    @GetMapping("/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "products/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Product product,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "products/new";

        dao.save(product);
        return "redirect:/products";
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
    public String delete(@PathVariable("id") int id) {
        dao.delete(id);
        return "redirect:/products";
    }
}
