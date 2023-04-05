package aa;

import java.util.Arrays;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ImportResource("application-context.xml")
public class Application extends SpringBootServletInitializer { //to make use of Spring Framework��s Servlet 3.0 support and lets you configure your application when it is launched by the servlet container. Servlet 3.0 ���忡 ���ο� ��� �� �ϳ��� web.xml ���� ������ �������� ���̴�.
//public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
	}
	

	  @Bean
	  public ServletWebServerFactory servletContainer(){
		    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		    tomcat.addAdditionalTomcatConnectors(createAjpConnector());
		    return tomcat;
	  }
	
	  @Value("${tomcat.ajp.port}") //mod_jk, AJP
	  private int ajpPort;
	  @Value("${tomcat.ajp.protocol}") //tomcat.ajp.protocol=AJP/1.3, HTTP/1.1
	  private String ajpProtocol;
	  private Connector createAjpConnector() {
		Connector ajpConnector = new Connector(ajpProtocol);
		ajpConnector.setPort(ajpPort);
		ajpConnector.setSecure(false);
		ajpConnector.setAllowTrace(false);
		ajpConnector.setScheme("http");
		//to avoid: java.lang.IllegalArgumentException: The AJP Connector is configured with secretRequired="true" but the secret attribute is either null or "". This combination is not valid.
		//((AbstractAjpProtocol<?>)ajpConnector.getProtocolHandler()).setSecretRequired(false);
		((AbstractAjpProtocol<?>)ajpConnector.getProtocolHandler()).setRequiredSecret(null);
		return ajpConnector;
	  }
	  

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			
		};
	}

}