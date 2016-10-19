package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.TrainerComment;

@Repository
public interface TrainerCommentRepository extends
		JpaRepository<TrainerComment, Integer> {

}
