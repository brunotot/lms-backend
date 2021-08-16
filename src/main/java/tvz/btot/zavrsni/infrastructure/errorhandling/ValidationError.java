package tvz.btot.zavrsni.infrastructure.errorhandling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {
    private String objectName;
    private String fieldName;
    private Object rejectedValue;
    private String messageCode;

    public ValidationError(String messageCode,
                           String objectName,
                           String fieldName,
                           Object rejectedValue) {
        this.objectName = objectName;
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.messageCode = messageCode;
    }
}
