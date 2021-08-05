package tvz.btot.zavrsni.domain;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private Integer id;
    private String name;
    private String description;
    private Subject subject;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private Float totalPoints;
    private List<Question> questions;
}
