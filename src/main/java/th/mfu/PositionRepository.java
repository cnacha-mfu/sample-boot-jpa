package th.mfu;


import org.springframework.data.repository.CrudRepository;

import th.mfu.domain.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {
    
}
