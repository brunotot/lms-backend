package tvz.btot.zavrsni.web.form;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectForm {
    private Integer id;
    private String name;
    private String description;
    private List<Integer> courseIds;
}
