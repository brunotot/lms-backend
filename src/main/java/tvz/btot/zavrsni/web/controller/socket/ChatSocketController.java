package tvz.btot.zavrsni.web.controller.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import tvz.btot.zavrsni.domain.SocketPayload;
import tvz.btot.zavrsni.infrastructure.service.MessageService;
import tvz.btot.zavrsni.web.dto.MessageDto;
import tvz.btot.zavrsni.web.form.MessageForm;

@Controller
public class ChatSocketController {
    private final SimpMessageSendingOperations sendingOperations;
    private final MessageService messageService;

    public ChatSocketController(final SimpMessageSendingOperations sendingOperations,
                                final MessageService messageService) {
        this.sendingOperations = sendingOperations;
        this.messageService = messageService;
    }

    @MessageMapping("/message/send/{chatId}")
    @SendTo("/message/receive/{chatId}")
    public MessageDto messageSend(final @Payload SocketPayload messagePayload) {
        MessageForm messageForm = messagePayload.get(MessageForm.class);
        return messageService.create(messageForm);
//        return JsonObject.builder()
//                .add("senderId", messageForm.getSenderId())
//                .add("message", messageForm.getText())
//                .add("dateCreated", DateTimeUtils.getCurrentDateString())
//                .stringify();
    }
}
