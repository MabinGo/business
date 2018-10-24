package cn.scb.sample.gateway;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class GatewayApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(GatewayApplication.class, args);
  }
}
