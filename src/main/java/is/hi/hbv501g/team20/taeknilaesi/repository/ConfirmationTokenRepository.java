package is.hi.hbv501g.team20.taeknilaesi.repository;

import org.springframework.data.repository.CrudRepository;

import is.hi.hbv501g.team20.taeknilaesi.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
