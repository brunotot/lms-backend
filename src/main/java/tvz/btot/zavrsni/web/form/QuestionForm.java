package tvz.btot.zavrsni.web.form;

import lombok.*;
import tvz.btot.zavrsni.domain.QuestionType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionForm {
    private Integer id;
    private QuestionType questionType;
    private String text;
    private Float points;
}
