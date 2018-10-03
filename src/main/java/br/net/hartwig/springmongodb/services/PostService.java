package br.net.hartwig.springmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.hartwig.springmongodb.domain.Post;
import br.net.hartwig.springmongodb.repository.PostRepository;
import br.net.hartwig.springmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {

		Optional<Post> post = repo.findById(id);

		return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));

	}

	public List<Post> findByTitle(String text) {
//		return repo.findByTitleContainingIgnoreCase(text);
		return repo.searchTitle(text);
	}

	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);

		return repo.fullSearch(text, minDate, maxDate);
	}

}
