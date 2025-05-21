package com.example.subscriptionservice.service;

import com.example.subscriptionservice.dto.SubscriptionDto;
import com.example.subscriptionservice.exception.ResourceNotFoundException;
import com.example.subscriptionservice.model.Subscription;
import com.example.subscriptionservice.model.User;
import com.example.subscriptionservice.repository.SubscriptionRepository;
import com.example.subscriptionservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public List<SubscriptionDto> getSubscriptionsByUserId(Long userId) {
        log.info("Fetching subscriptions for user with id: {}", userId);
        return subscriptionRepository.findByUserId(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SubscriptionDto addSubscription(Long userId, SubscriptionDto subscriptionDto) {
        log.info("Adding subscription for user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Subscription subscription = new Subscription();
        subscription.setServiceName(subscriptionDto.getServiceName());
        subscription.setPrice(subscriptionDto.getPrice());
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setUser(user);

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return convertToDto(savedSubscription);
    }

    public void deleteSubscription(Long userId, Long subscriptionId) {
        log.info("Deleting subscription with id: {} for user with id: {}", subscriptionId, userId);
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id: " + subscriptionId));

        if (!subscription.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Subscription not found for user with id: " + userId);
        }

        subscriptionRepository.delete(subscription);
    }

    public List<Object[]> getTop3PopularSubscriptions() {
        log.info("Fetching top 3 popular subscriptions");
        return subscriptionRepository.findTop3PopularSubscriptions();
    }

    private SubscriptionDto convertToDto(Subscription subscription) {
        return SubscriptionDto.builder()
                .id(subscription.getId())
                .serviceName(subscription.getServiceName())
                .price(subscription.getPrice())
                .startDate(subscription.getStartDate())
                .userId(subscription.getUser().getId())
                .build();
    }
}