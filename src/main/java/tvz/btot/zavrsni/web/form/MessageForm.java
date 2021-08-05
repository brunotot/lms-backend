package tvz.btot.zavrsni.web.form;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageForm {
    private Integer id;
    private String text;
    private Timestamp dateCreated;
    private Integer senderId;
    private Integer chatId;
}
