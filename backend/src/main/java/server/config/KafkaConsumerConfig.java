package server.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import server.domain.TicketTransactionMessage;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${spring.kafka.properties.security.protocol}")
  private String securityProtocal;

  @Value("${spring.kafka.properties.sasl.mechanism}")
  private String saslMechanism;

  @Value("${spring.kafka.properties.sasl.jaas.config}")
  private String saslJaasConfig;

  @Value("${spring.kafka.consumer.ssl.trust-store-location}")
  private String sslTrustStoreLocation;

  @Value("${spring.kafka.consumer.ssl.trust-store-password}")
  private String sslTrustStorePassword;

  @Value("${spring.kafka.consumer.group-id}")
  private String groupID;

  @Value("${spring.kafka.consumer.auto-offset-reset}")
  private String autoOffsetReset;

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocal);

    props.put(SaslConfigs.SASL_MECHANISM, saslMechanism);
    props.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
    props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, sslTrustStoreLocation);
    props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, sslTrustStorePassword);

    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_DOC, autoOffsetReset);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);

    return props;
  }

  @Bean
  public ConsumerFactory<String, TicketTransactionMessage> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        consumerConfigs(),
        new StringDeserializer(),
        new JsonDeserializer<>(TicketTransactionMessage.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, TicketTransactionMessage> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, TicketTransactionMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());

    return factory;
  }
}
