package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tvz.btot.zavrsni.infrastructure.service.ChatService;
import tvz.btot.zavrsni.infrastructure.service.MessageService;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;
import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.dto.MessageDto;

import java.util.List;

@RestController
@RequestMapping("/chat/{chatId}/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> findAllFromChat(@PathVariable Integer chatId) {
        return ResponseEntity
                .ok(messageService.findAllFromChat(chatId));
    }
}
