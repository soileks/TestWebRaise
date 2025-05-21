package com.example.subscriptionservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionDto {
    private Long id;
    @NotBlank(message = "Service name is required")
    private String serviceName;
    private Double price;
    private String startDate;
    private Long userId;
}