package tvz.btot.zavrsni.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private Integer id;
    private String name;
    private String description;
    private List<Integer> courseIds;
}
