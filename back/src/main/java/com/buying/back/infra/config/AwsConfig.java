package com.buying.back.infra.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

  @Bean
  public AWSCredentials awsCredentials(@Value("${byebuying.aws.access-key}") String accessKey,
    @Value("${byebuying.aws.secret-key}") String secretKey) {
    return new BasicAWSCredentials(accessKey, secretKey);
  }

  @Bean
  public AmazonSimpleEmailService awsSimpleEmailService(AWSCredentials awsCredentials,
    @Value("${byebuying.aws.ses.region}") String sesRegion) {
    return AmazonSimpleEmailServiceClientBuilder
      .standard()
      .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
      .withRegion(sesRegion)
      .build();
  }

}
