package com.example.luna_project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/zalo")
public class ZaloController {

    private final String APP_ID = "YOUR_APP_ID";
    private final String APP_SECRET = "YOUR_APP_SECRET";

    @GetMapping("/token")
    public ResponseEntity<?> getAccessToken(@RequestParam String code) {
        try {
            String url = "https://oauth.zaloapp.com/v4/access_token"
                    + "?app_id=" + APP_ID
                    + "&app_secret=" + APP_SECRET
                    + "&code=" + code
                    + "&grant_type=authorization_code";

            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String accessToken) {
        try {
            String url = "https://graph.zalo.me/v2.0/me?access_token=" + accessToken;
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> userProfile = restTemplate.getForObject(url, Map.class);

            return ResponseEntity.ok(userProfile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}

