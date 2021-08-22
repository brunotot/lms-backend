package tvz.btot.zavrsni.domain;

import lombok.*;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private Integer id;
    private AnnouncementType type;
    private String title;
    private String body;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private KeyValue creator;
    private KeyValue subject;

    public Integer getTypeId() {
        return type.ordinal() + 1;
    }
}
