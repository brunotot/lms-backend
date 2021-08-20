package tvz.btot.zavrsni.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {
    private Integer id;
    private String idString;
    private String value;
    private Object ValueObject;
}
