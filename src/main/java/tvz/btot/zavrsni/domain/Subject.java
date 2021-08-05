package tvz.btot.zavrsni.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    private Integer id;
    private String name;
    private String description;

}
