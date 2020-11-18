package is.hi.hbv501g.team20.taeknilaesi.repository;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>
{
    User findByEmail(String email);

    User findById(int id);
}
