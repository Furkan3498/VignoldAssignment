package de.vignold.vignoldmanagement.dao;

import de.vignold.vignoldmanagement.entity.Job;
import de.vignold.vignoldmanagement.entity.state.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    Long countByProduct_idAndState(Long productId, State state);
}
