package tvz.btot.zavrsni.web.form;

import lombok.*;
import tvz.btot.zavrsni.domain.KeyValue;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionForm {
    private Integer id;
    private String text;
    private Float points;
    private Integer examId;
    private KeyValue correctAnswer;
    private List<KeyValue> incorrectAnswers;
    private Integer selectedAnswer;
}
