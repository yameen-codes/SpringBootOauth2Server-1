package willydekeyser.config;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import ch.qos.logback.classic.Logger;
@Configuration
public class EmbeddedJdbcConfig {
  private static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmbeddedJdbcConfig.class);
  @Bean
  public DataSource dataSource() {
    try {
      var dbBuilder = new EmbeddedDatabaseBuilder();
      return dbBuilder.setType(EmbeddedDatabaseType.H2)
          .addScripts("classpath:h2/schema.sql", "classpath:h2/data.sql")
          .build();
    } catch (Exception e) {
      LOGGER.error("Embedded DataSource bean cannot be created!", e);
      return null;
    }
  }
}