package com.raufbisov.interaction;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class InteractionConfig {

  @Value("${rabbitmq.exchanges.internal}")
  private String internalExchange;

  @Value("${rabbitmq.queues.interaction}")
  private String interactionQueue;

  @Value("${rabbitmq.routing-keys.internal-interaction}")
  private String internalInteractionRoutingKey;

  @Bean
  public TopicExchange internalTopicExchange() {
    return new TopicExchange(this.internalExchange);
  }

  @Bean
  public Queue interactionQueue() {
    return new Queue(this.interactionQueue);
  }

  @Bean
  public Binding internalInteractionBinding() {
    return BindingBuilder
      .bind(interactionQueue())
      .to(internalTopicExchange())
      .with(this.internalInteractionRoutingKey);
  }
}
