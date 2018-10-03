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
import br.net.hartwig.springmongodb.dto.CommentDTO;
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

		CommentDTO comentario1 = new CommentDTO("Comentario de teste", simpleDateFormat.parse("02/10/2018"),
				new AuthorDTO(diego));
		CommentDTO comentario2 = new CommentDTO("Comentario de teste2", simpleDateFormat.parse("02/10/2018"),
				new AuthorDTO(aline));

		post1.getComments().addAll(Arrays.asList(comentario1, comentario2));

		postRepository.saveAll(Arrays.asList(post1, post2));

		diego.getPosts().addAll(Arrays.asList(post1));
		userRepository.save(diego);

		aline.getPosts().addAll(Arrays.asList(post2));
		userRepository.save(aline);

	}

}
