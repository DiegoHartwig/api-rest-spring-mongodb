package br.net.hartwig.springmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.net.hartwig.springmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
