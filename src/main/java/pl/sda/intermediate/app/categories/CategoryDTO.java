package pl.sda.intermediate.app.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Integer id;
    private String name;
    private Integer parentId;
    private CategoryState state;


    public String getText() {
        return name;
    }

    public String getParent() {
        if (parentId == null) {
            return "#";
        }
        return parentId.toString();
    }


}
