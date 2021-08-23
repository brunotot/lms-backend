package tvz.btot.zavrsni.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.infrastructure.service.ChatService;
import tvz.btot.zavrsni.security.preauthorization.AllowAnonymous;
import tvz.btot.zavrsni.security.preauthorization.AllowStudent;
import tvz.btot.zavrsni.web.controller.base.CrudController;
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
    @AllowStudent
    public List<ChatDto> findExistingChats() {
        final User thisUser = SecurityContextUtils.getUser();
        final Integer thisUserId = thisUser.getId();
        return chatService.findExistingChats(thisUserId);
    }

    @Override
    @GetMapping
    @AllowStudent
    public ResponseEntity<List<ChatDto>> findAll() {
        // TODO
        return null;
    }

    @Override
    @GetMapping("/{chatId}")
    @AllowStudent
    public ResponseEntity<ChatDto> findById(final @PathVariable Integer chatId) {
        return ResponseEntity
                .ok(chatService.findById(chatId));
    }

    @Override
    @PostMapping
    @AllowStudent
    public ResponseEntity<ChatDto> create(final ChatForm chatForm) {
        return ResponseEntity
                .ok(chatService.create(chatForm));
    }

    @Override
    @AllowStudent
    public ResponseEntity<ChatDto> update(final Integer chatId, final ChatForm chatForm) {
        // TODO
        return null;
    }

    @Override
    @AllowStudent
    public ResponseEntity<ChatForm> getFormById(final Integer chatId) {
        // TODO
        return null;
    }

    @Override
    @AllowStudent
    public ResponseEntity<Void> delete(final Integer chatId) {
        // TODO
        return null;
    }
}
