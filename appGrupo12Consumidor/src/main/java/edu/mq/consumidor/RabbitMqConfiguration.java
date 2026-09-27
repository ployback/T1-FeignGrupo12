package edu.mq.consumidor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    public static final String QUEUE_NAME = "Grupo1Queue";
    public static final String EXCHANGE_NAME = "Grupo1Exchange";
    public static final String ROUTING_KEY = "Grupo1Routing";

    @Bean
    Queue fibonacciQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    DirectExchange fibonacciExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding fibonacciBinding(Queue fibonacciQueue, DirectExchange fibonacciExchange) {
        return BindingBuilder.bind(fibonacciQueue).to(fibonacciExchange).with(ROUTING_KEY);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}