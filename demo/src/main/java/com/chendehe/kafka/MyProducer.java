package com.chendehe.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

class MyProducer {

  private static final String TOPIC = "chendehe1";
  private static final String URL = "localhost:9092";

  private MyProducer() {

  }

  public static void main(String[] args) {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, URL);
    props.put(ProducerConfig.ACKS_CONFIG, "all");
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    //配置partitionner选择策略，可选配置
    //props.put("partitioner.class", "com.chendehe.kafka.MyPartitioner");
    Producer<String, String> producer = new KafkaProducer<>(props);

    ////////
    ProducerRecord<String, String> data = new ProducerRecord<>(TOPIC, "K2", "V2");
    producer.send(data, (metadata, e) -> {
      if (e != null) {
        e.printStackTrace();
      } else {
        System.out.println("The offset of the record we just sent is: " + metadata.offset());
      }
    });
    ///////
    producer.close();
  }
}  