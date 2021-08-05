package tvz.btot.zavrsni.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private QuestionType questionType;
    private String text;
    private Float points;
}
