package com.btth.controller;

import com.btth.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/category")
    public String category(@RequestParam String category, Model model) {
        model.addAttribute("products", service.getByCategory(category));
        return "list";
    }

    @GetMapping("/hot")
    public String hot(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("products", service.getHot(page, 10));
        return "list";
    }

    @GetMapping("/mobile")
    @ResponseBody
    public Object mobile(@RequestParam(defaultValue = "0") int page) {
        return service.mobile(page, 10);
    }

    @GetMapping("/search")
    public String search(
            String keyword,
            Double minPrice,
            Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        try {
            model.addAttribute("products",
                    service.search(keyword, minPrice, maxPrice, page, 10));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "list";
    }
}
