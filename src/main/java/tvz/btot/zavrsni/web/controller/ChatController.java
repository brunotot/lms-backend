package tvz.btot.zavrsni.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tvz.btot.zavrsni.infrastructure.service.ChatService;
import tvz.btot.zavrsni.security.utils.SecurityContextUtils;
import tvz.btot.zavrsni.web.dto.ChatDto;
import tvz.btot.zavrsni.web.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(final ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/existing")
    public List<ChatDto> findExistingChats() {
        return chatService.findExistingChats(SecurityContextUtils.getUser().getId());
    }
}
