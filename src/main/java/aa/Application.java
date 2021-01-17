package aa;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ImportResource("application-context.xml")
public class Application extends SpringBootServletInitializer { //to make use of Spring Framework’s Servlet 3.0 support and lets you configure your application when it is launched by the servlet container. Servlet 3.0 스펙에 새로운 기능 중 하나는 web.xml 없이 배포가 가능해진 것이다.

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args).getBean(ScheduleCockpit.class).startScheduler();
		
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		
	}
	
	/* seems not used
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	*/

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