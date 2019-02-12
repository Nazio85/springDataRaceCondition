package ru.sbrf.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.rest.model.Account;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

}
