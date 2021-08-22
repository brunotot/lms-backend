package tvz.btot.zavrsni.web.form;

import lombok.*;
import tvz.btot.zavrsni.domain.AnnouncementType;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementForm {
    private Integer id;
    private AnnouncementType type;
    private String title;
    private String body;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private Integer creatorId;
    private Integer subjectId;
}
