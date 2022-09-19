package com.example.module4_minitesttuan1.controller;

import com.example.module4_minitesttuan1.model.Product;
import com.example.module4_minitesttuan1.model.ProductForm;
import com.example.module4_minitesttuan1.service.ServiceInterface.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:upload_file.properties")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public ModelAndView listCustomers() {
        List<Product> products = productService.findAll();
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute("product") ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(),
                productForm.getPrice(), fileName);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", new ProductForm());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/delete");
            modelAndView.addObject("product", product);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:products";
    }

    @GetMapping("/view/{id}")
    public String showDetail(@PathVariable long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("detail", product);
        return "/detail";
    }
}
