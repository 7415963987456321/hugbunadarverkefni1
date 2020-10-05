package is.hi.hbv501g.team20.taeknilaesi.repository;

import is.hi.hbv501g.team20.taeknilaesi.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer>
{
}