package com.example.order_streams.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.math.BigDecimal;

@Configuration
@EnableKafkaStreams
public class OrderStreamConfig {

    @Bean
    public KStream<String, String> orderStream(StreamsBuilder builder) {
        KStream<String, String> stream = builder.stream("orders");
        stream.foreach((key, value) -> System.out.println("📊 Received order in stream: " + value));
        return stream;
    }
}

