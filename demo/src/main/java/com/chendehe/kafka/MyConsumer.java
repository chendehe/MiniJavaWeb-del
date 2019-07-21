package com.chendehe.kafka;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class MyConsumer {

  private static final String TOPIC = "chendehe1";
  private static final String URL = "localhost:9092";

  public static void main(String[] args) {
    Properties props = new Properties();

    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, URL);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    // latest, earliest, none
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
    props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    Consumer<String, String> consumer = new KafkaConsumer<>(props);

    consumer.subscribe(Collections.singletonList(TOPIC));

    ConsumerRecords<String, String> records;
    for (; ; ) {
      records = consumer.poll(100);
      System.out.println("SIZE" + records.count());
      if (!records.isEmpty()) {
        break;
      }
    }

    for (ConsumerRecord<String, String> record : records) {
      System.out.printf("offset = %d, key = %s, value = %s%n",
          record.offset(), record.key(), record.value());
    }
  }

}