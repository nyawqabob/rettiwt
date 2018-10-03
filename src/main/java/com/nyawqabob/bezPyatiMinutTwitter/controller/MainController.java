package com.nyawqabob.bezPyatiMinutTwitter.controller;

import com.nyawqabob.bezPyatiMinutTwitter.entity.Message;
import com.nyawqabob.bezPyatiMinutTwitter.entity.User;
import com.nyawqabob.bezPyatiMinutTwitter.repos.MessageRepo;
import com.nyawqabob.bezPyatiMinutTwitter.service.UserService;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    @Value("${upload.path}") // достает переменную из application.properties
    private String uploadPath;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Map<String, Object> model) {

        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        model.put("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text,
            @RequestParam String tag,
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file, Map<String, Object> model) throws IOException {
        // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultName = uuidFile + "." + file.getName();
            file.transferTo(new File(uploadPath + "/" + resultName));
            message.setFilename(resultName);
        }
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put(
                "messages", messages);

        return "main";
    }

    @GetMapping("/profile")
    public String main() {

        return "profile";
    }
}
