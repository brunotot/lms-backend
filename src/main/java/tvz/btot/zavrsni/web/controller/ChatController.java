package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.service.ChatService;
import tvz.btot.zavrsni.infrastructure.service.CrudController;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;
import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.form.ChatForm;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController implements CrudController<ChatDto, ChatForm, Integer> {
    private final ChatService chatService;

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/existing")
    public List<ChatDto> findExistingChats() {
        final User thisUser = SecurityContextUtils.getUser();
        final Integer thisUserId = thisUser.getId();
        return chatService.findExistingChats(thisUserId);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ChatDto>> findAll() {
        // TODO
        return null;
    }

    @Override
    @GetMapping("/{chatId}")
    public ResponseEntity<ChatDto> findById(final @PathVariable Integer chatId) {
        return ResponseEntity
                .ok(chatService.findById(chatId));
    }

    @Override
    @PostMapping
    public ResponseEntity<ChatDto> create(final ChatForm chatForm) {
        return ResponseEntity
                .ok(chatService.create(chatForm));
    }

    @Override
    public ResponseEntity<ChatDto> update(final Integer chatId, final ChatForm chatForm) {
        // TODO
        return null;
    }

    @Override
    public ResponseEntity<ChatForm> getFormById(final Integer chatId) {
        // TODO
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(final Integer chatId) {
        // TODO
        return null;
    }
}
