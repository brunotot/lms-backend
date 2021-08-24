package tvz.btot.zavrsni.web.controller.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import tvz.btot.zavrsni.domain.SocketPayload;
import tvz.btot.zavrsni.infrastructure.service.ChatService;
import tvz.btot.zavrsni.infrastructure.service.MessageService;
import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.dto.MessageDto;
import tvz.btot.zavrsni.web.form.ChatForm;
import tvz.btot.zavrsni.web.form.MessageForm;

@Controller
public class ChatSocketController {
    private final MessageService messageService;
    private final ChatService chatService;
    private final SimpMessageSendingOperations messageSendingOperations;

    public ChatSocketController(final MessageService messageService,
                                final SimpMessageSendingOperations messageSendingOperations,
                                final ChatService chatService) {
        this.messageService = messageService;
        this.messageSendingOperations = messageSendingOperations;
        this.chatService = chatService;
    }

    @MessageMapping("/message/send/{chatId}")
    public void messageSend(final @Payload SocketPayload messagePayload) {
        MessageForm messageForm = messagePayload.get(MessageForm.class);
        MessageDto createdMessage = messageService.create(messageForm);
        Integer chatId = createdMessage.getChatId();
        ChatDto chatDto = chatService.findById(chatId);
        chatDto.getMultipleChatUsers().forEach(mcu -> messageSendingOperations.convertAndSend(String.format("/message/receive/%s", mcu.getId()), createdMessage));
    }

    @MessageMapping("/chat/create")
    public void chatCreate(final @Payload SocketPayload chatPayload) {
        ChatForm chatForm = chatPayload.get(ChatForm.class);
        ChatDto createdChat = chatService.create(chatForm);
        createdChat.getMultipleChatUsers().forEach(mcu -> messageSendingOperations.convertAndSend(String.format("/chat/create/%s", mcu.getId()), createdChat));
    }
}
