package config;

import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile("default")
@PropertySource("classpath:app.properties")
public class PropertyDrivenConfig {

  private final ApplicationContext applicationContext;

  public PropertyDrivenConfig(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Value("${dao.target:dao}")
  private String target;

  @Bean(name = "dao")
  @DependsOn("propertySourcesPlaceholderConfigurer")
  public IDao selectedDao() {
    IDao bean = applicationContext.getBeansOfType(IDao.class).get(target);
    if (bean == null) {
      throw new IllegalArgumentException("Implémentation inconnue: " + target + " (dao|dao2|daoFile|daoApi)");
    }
    return bean;
  }

  // Résolution des placeholders @Value sans Spring Boot
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
