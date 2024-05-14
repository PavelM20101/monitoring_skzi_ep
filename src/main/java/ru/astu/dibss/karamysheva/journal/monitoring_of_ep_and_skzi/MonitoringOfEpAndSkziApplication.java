package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.repository")
@EnableJpaAuditing
public class MonitoringOfEpAndSkziApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringOfEpAndSkziApplication.class, args);
    }

}
