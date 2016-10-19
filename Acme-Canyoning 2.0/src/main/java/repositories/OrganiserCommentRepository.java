package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.OrganiserComment;

@Repository
public interface OrganiserCommentRepository extends
		JpaRepository<OrganiserComment, Integer> {

}
