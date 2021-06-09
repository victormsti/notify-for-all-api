package com.notifyforall.api.repository;

import com.notifyforall.api.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {

    Optional<App> findByIdAndUserAppId(Long id, Long idUserApp);

    Optional<App> findByIdentifierNameAndUserAppId(String identifierName, Long idUserApp);
}
