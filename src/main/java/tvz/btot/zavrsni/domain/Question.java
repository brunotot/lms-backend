package tvz.btot.zavrsni.domain;

import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private String text;
    private Float points;
    private KeyValue exam;
    private KeyValue correctAnswer;
    private List<KeyValue> incorrectAnswers;
    private Integer selectedAnswer;

    public String getIncorrectAnswersQuery() {
        return Optional.ofNullable(incorrectAnswers)
                .orElse(List.of())
                .stream()
                .map(a -> String.valueOf(a.getId()))
                .collect(Collectors.joining(","));
    }
}
