package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slackbot")
@RequiredArgsConstructor
public class SlackBotController {
    @GetMapping("/test")
    public void webHook(){
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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");    //웹훅URL
        api.call(new SlackMessage("#general", "TEST-WEBHOOK", "연습~~~~"));
    }
}
