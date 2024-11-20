package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    @GetMapping("/joke")
    public String getJoke() {
        // Return the joke in big bold letters
        return "<h1 style='font-size: 50px; color: #FF5733; font-weight: bold;'>Happy Children's Day Rinuma and the one and only hero Izhaan</h1>";
    }
}
