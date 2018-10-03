package br.net.hartwig.springmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.net.hartwig.springmongodb.domain.Post;
import br.net.hartwig.springmongodb.domain.User;
import br.net.hartwig.springmongodb.dto.AuthorDTO;
import br.net.hartwig.springmongodb.repository.PostRepository;
import br.net.hartwig.springmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		// Carga Inicial

		User diego = new User(null, "Diego Hartwig", "hartwig.diego@gmail.com");
		User aline = new User(null, "Aline Cardoso", "acardoso@gmail.com");

		userRepository.saveAll(Arrays.asList(diego, aline));

		Post post1 = new Post(null, simpleDateFormat.parse("02/10/2018"), "Primeiro post",
				"Esse é o primeiro post de teste", new AuthorDTO(diego));
		Post post2 = new Post(null, simpleDateFormat.parse("02/10/2018"), "Segundo post",
				"Esse é o segundo post de teste", new AuthorDTO(aline));

		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}
