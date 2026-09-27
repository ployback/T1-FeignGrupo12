package edu.mq.productor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FibonacciRequestService {
    private final RabbitTemplate rabbitTemplate;

    public FibonacciRequestService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String numbers) {
        List<Integer> positions = parsePositions(numbers);
        rabbitTemplate.convertAndSend(
                RabbitMqConfiguration.EXCHANGE_NAME,
                RabbitMqConfiguration.ROUTING_KEY,
                positions);
    }

    private List<Integer> parsePositions(String numbers) {
        if (numbers == null || numbers.isBlank()) {
            throw new IllegalArgumentException("El parámetro numbers es obligatorio.");
        }
        try {
            return Arrays.stream(numbers.split(";", -1))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("numbers debe contener enteros separados por ';'.", exception);
        }
    }
}