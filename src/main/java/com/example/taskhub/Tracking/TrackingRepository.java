package com.example.taskhub.Tracking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrackingRepository extends JpaRepository<Tracking, String> {
    List<Tracking> findTrackingsByProjectIdAndDeletedAtIsNull(String idProject);
    Optional<Tracking> findTrackingByIdAndDeletedAtIsNull(String idTracking);
}
