package edu.mq.productor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {
    private final FibonacciRequestService requestService;

    public FibonacciController(FibonacciRequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/api/fibonacci/send")
    public ResponseEntity<String> send(@RequestParam String numbers) {
        requestService.send(numbers);
        return ResponseEntity.ok("Lista enviada a rabbit mq ok");
    }
}