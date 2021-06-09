package com.notifyforall.api.repository;

import com.notifyforall.api.model.EmailUserAppConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailConfigurationRepository extends JpaRepository<EmailUserAppConfiguration, Long> {

    public Optional<EmailUserAppConfiguration> findByIdAndAppIdAndAppUserAppId(Long idEmailConfiguration, Long idApp, Long idUserApp);

    public List<EmailUserAppConfiguration> findByAppIdAndAppUserAppId(Long idApp, Long idUserApp);
}
