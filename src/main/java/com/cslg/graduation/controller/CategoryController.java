package com.cslg.graduation.controller;

import com.cslg.graduation.dto.CategoryOutputDTO;
import com.cslg.graduation.entity.Category;
import com.cslg.graduation.entity.PageResult;
import com.cslg.graduation.entity.User;
import com.cslg.graduation.service.CategoryService;
import com.cslg.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @PostMapping("/findPage")
    public PageResult<Category> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return categoryService.findPage(searchMap, page, size);
    }

    @GetMapping("/findById")
    public String findById(Integer id, HttpSession httpSession) {
        Category category = categoryService.findById(id);
        CategoryOutputDTO categoryOutputDTO = new CategoryOutputDTO();
        categoryOutputDTO.setName(category.getName());
        categoryOutputDTO.setUsername(userService.findById(category.getMasterId()).getUsername());
        httpSession.setAttribute("category", categoryOutputDTO);
        List<User> userList = userService.findAll();
        httpSession.setAttribute("userList", userList);
        return "/admin/categoryUpdate";
    }

    @PostMapping("/add")
    public String add(CategoryOutputDTO categoryOutputDTO) {
        Category category = new Category();
        category.setName(categoryOutputDTO.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("username", categoryOutputDTO.getUsername());
        category.setMasterId(userService.findList(map).get(0).getId());
        categoryService.add(category);
        return "redirect:/admin/category";
    }

    @PostMapping("/update")
    public String update(CategoryOutputDTO categoryOutputDTO) {
        Category category = new Category();
        category.setName(categoryOutputDTO.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("username", categoryOutputDTO.getUsername());
        System.out.println(categoryOutputDTO.getUsername());
        category.setMasterId(userService.findList(map).get(0).getId());
        categoryService.update(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete")
    public String delete(Integer id) {
        categoryService.delete(id);
        return "redirect:/admin/category";
    }

}
