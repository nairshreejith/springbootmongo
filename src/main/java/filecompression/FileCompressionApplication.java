package filecompression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"filecompression", "filecompression.models","filecompression.controller","filecompression.service"})
public class FileCompressionApplication {
	public static void main(String[] args) {
        SpringApplication.run(FileCompressionApplication.class, args);
    }
}
