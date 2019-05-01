package ua.donskykh.mybookstore.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.Category;
import ua.donskykh.mybookstore.repo.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service ("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Qualifier("categoryRepository")
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public String saveCategory(Category category) {
        category.setActive(true);
        categoryRepository.saveAndFlush(category);
        return "Save is sucessfully";
    }

    @Override
    @Transactional
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        List<Category> categoriesCopy = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        categoriesCopy.addAll(categories);
        for (Category c : categories) {
            System.out.println(c);
            if (!c.isActive()) {
                categoriesCopy.remove(c);
            }
        }
        return categoriesCopy;
    }

    @Transactional
    public Category findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).get();
        if (!category.isActive()) {
            category = null;
        }
        return category;
    }

    @Override
    @Transactional
    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).get();
        if(!category.isActive()) {
            System.out.println("Category not found!");
        }
        category.setActive(false);
        categoryRepository.saveAndFlush(category);
        return "Delete successfully";
    }

    @Override
    @Transactional
    public String updateCategory(Category category) {
        categoryRepository.saveAndFlush(category);
        return "Update is successfully";
    }
}
