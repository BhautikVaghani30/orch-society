package com.town.orch.processor.repository;

import com.town.orch.processor.entities.Society;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Reactive repository interface for managing {@link Society} entities.
 *
 * <p>Provides standard CRUD operations for the 'society' table using R2DBC.
 */
@Repository
public interface TownSocietyRepository extends ReactiveCrudRepository<Society, String> {}
