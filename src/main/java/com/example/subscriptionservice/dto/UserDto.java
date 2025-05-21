package com.example.subscriptionservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private String email;
    private List<SubscriptionDto> subscriptions;
}