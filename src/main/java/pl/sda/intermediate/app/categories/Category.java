package pl.sda.intermediate.app.categories;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
public class Category {
    private Integer id;
    private String name;
    @Setter
    private Integer parentId;
    private Integer depth;





}
