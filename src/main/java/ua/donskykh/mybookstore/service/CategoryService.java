package ua.donskykh.mybookstore.service;

import ua.donskykh.mybookstore.domain.Category;

import java.util.List;

public interface CategoryService {

    String saveCategory(Category category);

    List<Category> findAllCategories();

    Category findCategoryById(Long id);

    String deleteCategory(Long id);

    String updateCategory(Category catBranch);

}
