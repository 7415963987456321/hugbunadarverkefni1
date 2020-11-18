package is.hi.hbv501g.team20.taeknilaesi.repository;

import is.hi.hbv501g.team20.taeknilaesi.model.Comment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

	List<Comment> findAllById(int id);

	List<Comment> findByLessonId(int id);
}
