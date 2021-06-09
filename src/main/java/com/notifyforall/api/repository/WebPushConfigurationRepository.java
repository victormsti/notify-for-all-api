package com.notifyforall.api.repository;

import com.notifyforall.api.model.WebPushUserAppConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebPushConfigurationRepository extends JpaRepository<WebPushUserAppConfiguration, Long> {

    Optional<WebPushUserAppConfiguration> findByIdAndAppIdAndAppUserAppId(Long idWebPushConfiguration, Long idApp, Long idUserApp);
}
