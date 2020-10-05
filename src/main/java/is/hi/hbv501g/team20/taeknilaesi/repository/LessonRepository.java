package is.hi.hbv501g.team20.taeknilaesi.repository;

import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer>
{
   // Iterable<Object> findAllById(int id);
}

