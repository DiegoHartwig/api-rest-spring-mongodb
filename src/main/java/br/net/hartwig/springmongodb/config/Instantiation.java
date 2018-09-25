package br.net.hartwig.springmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.net.hartwig.springmongodb.domain.User;
import br.net.hartwig.springmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();

		User diego = new User(null, "Diego Hartwig", "hartwig.diego@gmail.com");
		User aline = new User(null, "Aline Cardoso", "acardoso@gmail.com");

		userRepository.saveAll(Arrays.asList(diego, aline));

	}

}
