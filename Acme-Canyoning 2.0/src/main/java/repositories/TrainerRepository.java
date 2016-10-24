package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	@Query("select t from Trainer t where t.userAccount.id=?1")
	Trainer findByUserAccountId(int userAccountId);
	
	@Query("select t from Trainer t where ((select count(t) from Course c where c.trainer = t.id) > " +
			"1.10*((select count(c) from Course c)/(select count(t) from Trainer t))) or " +
			"((select count(c) from Course c where c.trainer = t.id) < " +
			"0.90*((select count(c) from Course c)/(select count(t) from Trainer t)))")
	Collection<Trainer> courses10percAboveBelowAvg();
}
