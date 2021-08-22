package tvz.btot.zavrsni.web.dto;

import lombok.*;
import tvz.btot.zavrsni.domain.AnnouncementType;
import tvz.btot.zavrsni.domain.ChatType;
import tvz.btot.zavrsni.domain.KeyValue;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementDto {
    private Integer id;
    private AnnouncementType type;
    private String title;
    private String body;
    private String dateStart;
    private String dateEnd;
    private KeyValue creator;
    private KeyValue subject;
}
