package com.example.subscriptionservice.controller;

import com.example.subscriptionservice.dto.SubscriptionDto;
import com.example.subscriptionservice.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/users/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDto>> getUserSubscriptions(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionsByUserId(id));
    }

    @PostMapping("/users/{id}/subscriptions")
    public ResponseEntity<SubscriptionDto> addSubscription(
            @PathVariable Long id,
            @Valid @RequestBody SubscriptionDto subscriptionDto) {
        return new ResponseEntity<>(
                subscriptionService.addSubscription(id, subscriptionDto),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(
            @PathVariable Long userId,
            @Valid @PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(userId, subscriptionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<Object[]>> getTop3PopularSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getTop3PopularSubscriptions());
    }
}