package pl.sda.intermediate;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate.app.categories.CategoryDTO;
import pl.sda.intermediate.app.categories.CategoryService;

import java.util.List;

class CategoryServiceTest {

    @Test
    void shouldMarkAsOpen() {

        String name = "Lektury";
        CategoryService categoryService = new CategoryService();
        List<CategoryDTO> categoryDTOS = categoryService.findCategories(name);

        System.out.println(categoryDTOS);
    }

    @Test
    void shouldMarkAsOpenWhenNameIsNull() {

        String name = "null";
        CategoryService categoryService = new CategoryService();
        List<CategoryDTO> categoryDTOS = categoryService.findCategories(name);

        System.out.println(categoryDTOS);
    }
}