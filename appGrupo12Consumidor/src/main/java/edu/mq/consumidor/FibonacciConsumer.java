package edu.mq.consumidor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FibonacciConsumer {
    private final FibonacciService fibonacciService;
    private final long processingDelayMillis;

    public FibonacciConsumer(FibonacciService fibonacciService,
                             @Value("${fibonacci.processing-delay-ms:20000}") long processingDelayMillis) {
        this.fibonacciService = fibonacciService;
        this.processingDelayMillis = processingDelayMillis;
    }

    @RabbitListener(queues = RabbitMqConfiguration.QUEUE_NAME)
    public void receive(List<Integer> positions) {
        try {
            Thread.sleep(processingDelayMillis);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            return;
        }
        List<Long> results = fibonacciService.calculateSequence(positions);
        System.out.println("Posiciones recibidas: " + positions + " -> Fibonacci: " + results);
    }
}