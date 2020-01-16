package pl.sda.intermediate.app.categories;

import lombok.Getter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CategoryDAO {

    private static CategoryDAO instance;
    @Getter
    List<Category> categories;

    private CategoryDAO() {
        categories = createCategories();
    }


    private List<Category> createCategories() {
        List<String> lines = readLinesFromFile();
        List<Category> categories = convertLinesToCategories(lines);
        Map<Integer, List<Category>> categoriesMap = categories.stream()
                .collect(Collectors.groupingBy(x -> x.getDepth()));
        populateParentId(0,categoriesMap);
        return categories;
    }

    private void populateParentId (int depth, Map<Integer, List<Category>> categoriesMap) {
        if (!categoriesMap.containsKey(depth)) {
            return;
        }
        List<Category> children = categoriesMap.get(depth);
        List<Category> potentialParents = categoriesMap.get(depth - 1);
        for (Category child : children) {
            if (potentialParents != null) {
                chooseParentId(potentialParents, child);
            }
        }
        populateParentId(depth + 1, categoriesMap);
    }

    private void chooseParentId(List<Category> potentialParents, Category child){
       Integer parentId = potentialParents.stream()
                .map(x->x.getId())
                .filter(x -> x < child.getId())
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(null);
        child.setParentId(parentId);
    }

    private List<Category> convertLinesToCategories(List<String> lines) {
        List<Category> categories = new ArrayList<>();
        int i = 1;
        for (String line: lines) {
            Category category = Category.builder()
                    .id(i++)
                    .name(line.trim())
                    .depth(calculateDepth(line))
                    .build();
            categories.add(category);
        }
        return categories;
    }

    private int calculateDepth(String line) {
        if (!line.startsWith(" ")) {
            return 0;
        }
        return line.split("\\S+")[0].length();
    }

    private List<String> readLinesFromFile() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL url = classLoader.getResource("Kategorie.txt");

        try {
            return Files.readAllLines(Paths.get(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
       return Collections.emptyList();
    }

    public static CategoryDAO getInstance() {
        if (instance == null) {
            synchronized (CategoryDAO.class) {
                if (instance == null) {
                    instance = new CategoryDAO();
                }
            }
        }
        return instance;
    }
}
