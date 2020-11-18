package is.hi.hbv501g.team20.taeknilaesi.repository;

import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer>
{
   // Iterable<Object> findAllById(int id);
  //  List<Lesson> findByCourseIdIn(int courseId);
   
   // List<Lesson> findAllByCourseId(int courseId);

// Iterable<Lesson> findByCourseId(Set<Integer> singleton);
}

 
