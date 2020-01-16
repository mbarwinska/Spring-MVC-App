package pl.sda.intermediate.app.categories;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    public List<CategoryDTO> findCategories(String categoryName) {
        List<Category> categories = CategoryDAO.getInstance().getCategories();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOS.add(convertToDTO(category));
        }
        if (categoryName != null) {
            markAsSelectedOrOpen(categoryName, categoryDTOS);
        }
        return categoryDTOS;
    }

    private void markAsSelectedOrOpen(String categoryName, List<CategoryDTO> categoryDTOS) {
        for (CategoryDTO categoryDTO : categoryDTOS) {
            if (categoryDTO.getName().contains(categoryName)) {
                categoryDTO.getState().setSelected(true);
                categoryDTO.getState().setOpened(true);
                openParent(categoryDTO, categoryDTOS);
            }
        }
    }

    private void openParent(CategoryDTO currentCategory, List<CategoryDTO> otherCategories) {
        if (currentCategory.getParentId() == null) {
            return;
        }
        CategoryDTO parent = null;
        for (CategoryDTO potentialParent : otherCategories) {
            if (potentialParent.getId().equals(currentCategory.getParentId())) {
                potentialParent.getState().setOpened(true);
                parent = potentialParent;
                break;
            }
        }
        openParent(parent, otherCategories);

    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setState(new CategoryState());
        return categoryDTO;
    }
}
