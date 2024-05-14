package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.configuration;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class ApplicationConfig {
    private static final String POSTGRES = "postgres";

    @Bean
    public DataSource dataSource() {
        final DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/monitoring?currentSchema=monitoring_of_ep_and_skzi");
        dataSourceBuilder.username(POSTGRES);
        dataSourceBuilder.password(POSTGRES);
        return dataSourceBuilder.build();
    }

//    @Bean
//    public SpringLiquibase liquibase() {
//        final SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource());
//        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
//        liquibase.setDefaultSchema("monitoring_of_ep_and_skzi");
//        return liquibase;
//    }

}
