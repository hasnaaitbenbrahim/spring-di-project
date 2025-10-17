package config;

import dao.DaoApi;
import dao.IDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;

@Configuration
@Profile("api")
public class DaoAliasConfig {
  // Alias "dao" qui renvoie DaoApi comme implémentation
  @Bean(name = "dao")
  public IDao daoAlias(DaoApi target) {
    return target; // alias "dao" -> "daoApi"
  }
}