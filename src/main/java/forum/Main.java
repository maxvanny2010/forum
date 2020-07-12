package forum;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Main.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/16/2020
 */
@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    /**
     * Method a pointer to program.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        SpringApplication.run(Main.class);
    }

    @Override
    protected final SpringApplicationBuilder configure(
            final SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    @Bean
    public SpringLiquibase liquibase(@Qualifier("dataSource") final DataSource ds) {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }
}
