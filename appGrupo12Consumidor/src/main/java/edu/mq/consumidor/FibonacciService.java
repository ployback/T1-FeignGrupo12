package edu.mq.consumidor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FibonacciService {
    private static final int MAX_LONG_POSITION = 92;
    private final Map<Integer, Long> cache = new ConcurrentHashMap<>();

    public FibonacciService() {
        cache.put(0, 0L);
        cache.put(1, 1L);
    }

    public long fibonacci(int position) {
        if (position < 0 || position > MAX_LONG_POSITION) {
            throw new IllegalArgumentException("La posición debe estar entre 0 y 92.");
        }
        Long cachedValue = cache.get(position);
        if (cachedValue != null) {
            return cachedValue;
        }
        long result = fibonacci(position - 1) + fibonacci(position - 2);
        cache.putIfAbsent(position, result);
        return cache.get(position);
    }

    public List<Long> calculateSequence(List<Integer> positions) {
        return positions.stream().map(this::fibonacci).toList();
    }
}