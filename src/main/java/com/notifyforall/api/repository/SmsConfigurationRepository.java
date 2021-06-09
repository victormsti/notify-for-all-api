package com.notifyforall.api.repository;

import com.notifyforall.api.model.EmailUserAppConfiguration;
import com.notifyforall.api.model.SmsUserAppConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SmsConfigurationRepository extends JpaRepository<SmsUserAppConfiguration, Long> {

    public Optional<SmsUserAppConfiguration> findByIdAndAppIdAndAppUserAppId(Long idSmsConfiguration, Long idApp, Long idUserApp);

    public List<SmsUserAppConfiguration> findByAppIdAndAppUserAppId(Long idApp, Long idUserApp);

}
