package com.qtsneaker.ShopFrontEnd.controller;

import com.qtsneaker.ShopFrontEnd.services.Category.CategoryService;
import com.qtsneaker.ShopFrontEnd.services.Product.ProductService;
import com.qtsneaker.common.entity.Category;
import com.qtsneaker.common.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired private ProductService productService;

    @GetMapping("/")
    public String Home(Model model, HttpSession session, HttpServletRequest request) {

        List<Category> listNoChildrenCategories = categoryService.listNoChildrenCategories();
        session.setAttribute("listNoChildrenCategories",listNoChildrenCategories);

        List<Product> listProductNewHomePage = productService.productNewHomePage();
        model.addAttribute("listProduct",listProductNewHomePage);

        return "homePage";
    }
    @GetMapping("/login")
    public String viewLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "register/login_form";
        }

        return "redirect:/";
    }

}
