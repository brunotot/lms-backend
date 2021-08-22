package tvz.btot.zavrsni.web.dto;

import lombok.*;
import tvz.btot.zavrsni.domain.KeyValue;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {
    private Integer id;
    private String text;
    private Float points;
    private KeyValue exam;
    private List<KeyValue> answers;
    private Integer selectedAnswer;
}
