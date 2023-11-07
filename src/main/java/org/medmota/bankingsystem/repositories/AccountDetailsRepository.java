package org.medmota.bankingsystem.repositories;

import org.medmota.bankingsystem.entities.AccountDetailsQueryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends CrudRepository<AccountDetailsQueryEntity, String> {

}
