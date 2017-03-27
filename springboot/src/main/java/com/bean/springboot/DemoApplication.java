package com.bean.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages ={"com.bean"} )
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication app =new SpringApplication(DemoApplication.class);
//		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		try {
			log.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://localhost:{}\n\t" +
                            "External: \thttp://{}:{}\n----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    env.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port"));
		} catch (UnknownHostException e) {
			log.error(e.getMessage());
		}

		String configServerStatus = env.getProperty("configserver.status");
		log.info("\n----------------------------------------------------------\n\t" +
						"Config Server: \t{}\n----------------------------------------------------------",
				configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
	}
}
