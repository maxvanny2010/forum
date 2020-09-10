package forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * BootConfig.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/16/2020
 */
@Configuration
public class WebConfig {
    public WebConfig() {
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver bean =
                new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
