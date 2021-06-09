package com.notifyforall.api.repository;

import com.notifyforall.api.enums.ChannelType;
import com.notifyforall.api.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByAppIdAndChannelTypeAndAppUserAppId(Long idApp, ChannelType channelType, Long idUserApp);

    Notification findByIdAndAppIdAndAppUserAppId(Long idNotification, Long idApp,  Long idUserApp);
}
