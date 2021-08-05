package tvz.btot.zavrsni.web.form;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectForm {
    private Integer id;
    private String name;
    private String description;
}
