import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.sql.SQLException;
import java.util.HashMap;
import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan("entity.package.location")
@EnableJpaRepositories("repository.package.location")
public class AppConfig {
	
	@Autowired 
	DataSource dataSource;
    
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean  getEntityManagerFactory() throws SQLException {

	  	LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setPackagesToScan(new String[]{"package.one", "package.two"});
	
	    factory.setDataSource(dataSource);
	
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    factory.setJpaVendorAdapter(vendorAdapter);
	
	    HashMap<String, Object> properties = new HashMap<>();
	    properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.format_sql", "true");
	    properties.put("hibernate.use_sql_comments", "true");
	    factory.setJpaPropertyMap(properties);
	
	    return factory;
	} 
}