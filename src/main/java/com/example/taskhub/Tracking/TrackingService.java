package com.example.taskhub.Tracking;

import com.example.taskhub.Exceptions.ExceptionsDetails;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectException;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectNotFoundException;
import com.example.taskhub.Exceptions.TrackingExceptions.TrackingException;
import com.example.taskhub.Exceptions.TrackingExceptions.TrackingNotFoundException;
import com.example.taskhub.Exceptions.UserExceptions.UserNotFoundException;
import com.example.taskhub.Tracking.DTO.CreateTrackingDTO;
import com.example.taskhub.Tracking.DTO.UpdateTrackingDTO;
import com.example.taskhub.User.User;
import com.example.taskhub.User.UserRepository;
import com.example.taskhub.Util.ServiceResponse;
import com.example.taskhub.project.Project;
import com.example.taskhub.project.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrackingService {
    private final TrackingRepository trackingRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public TrackingService(TrackingRepository trackingRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.trackingRepository = trackingRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> findAllTrackingByProjectId(String idProject) {
        if(idProject == null || idProject.isEmpty()) {
            throw new TrackingException("The id is null or empty",
                    new ExceptionsDetails(false, "An error has occurred with the parameters sent", null));
        }

        Project existingProject = this.projectRepository.findById(idProject).orElseThrow(
                () -> new TrackingNotFoundException("The project does not exist",
                        new ExceptionsDetails(false, "The project for which you would like to track does not exist", null))
        );

        ServiceResponse response = new ServiceResponse();

        List<Tracking> result = this.trackingRepository.findTrackingsByProjectIdAndDeletedAtIsNull(idProject);

        if(result.isEmpty()) {
            response.setSuccess(true);
            response.setMessage("Project with no recorded tracking");
            response.setData(result);

            return ResponseEntity.ok(response);
        }

        response.setSuccess(true);
        response.setMessage("Tracking found correctly");
        response.setData(result);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> findSingleTrackingById(String idProject, String idTracking) {
        if(idProject == null || idProject.isEmpty()) {
            throw new ProjectException("The idProject is null or empty",
                    new ExceptionsDetails(false, "An error has occurred with the parameters sent by the URL", null));
        }

        if(idTracking == null || idTracking.isEmpty()) {
            throw new TrackingException("The idTracking is null or empty",
                    new ExceptionsDetails(false, "An error has occurred with the parameters sent by the URL", null));
        }

        Project project = this.projectRepository.findProjectByIdAndDeletedAtIsNull(idProject).orElseThrow(
                () -> new ProjectNotFoundException("The project does not exist",
                        new ExceptionsDetails(false, "The project you are consulting does not exist", null))
        );

        Tracking tracking = this.trackingRepository.findTrackingByIdAndDeletedAtIsNull(idTracking).orElseThrow(
                () -> new TrackingNotFoundException("The tracking does not exist",
                        new ExceptionsDetails(false, "The tracking you are consulting does not exist", null))
        );

        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("Tracking found successful");
        response.setData(tracking);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> createTracking(CreateTrackingDTO tracking) {
        Project project = this.projectRepository.findProjectByIdAndDeletedAtIsNull(tracking.getProject_id()).orElseThrow(
                () -> new ProjectNotFoundException("The project does not exist",
                        new ExceptionsDetails(false, "The project you are trying to find does not exist", null))
        );

        User user = this.userRepository.findByIdAndDeletedAtIsNull(tracking.getCreatedBy()).orElseThrow(
                () -> new UserNotFoundException("The user does not exist",
                        new ExceptionsDetails(false, "The user with which you are trying to register the tracking does not exist", null))
        );

        Tracking newTracking = new Tracking(tracking, project);

        this.trackingRepository.save(newTracking);

        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("Tracking was successfully created");
        response.setData(newTracking);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> updateTracking(String idProject, String idTracking, UpdateTrackingDTO tracking) {
        Project existingProject = this.projectRepository.findProjectByIdAndDeletedAtIsNull(idProject).orElseThrow(
                () -> new ProjectNotFoundException("The project does not exist",
                        new ExceptionsDetails(false, "The project for which you are trying to update the tracking does not exist", null))
        );

        Tracking existingTracking = this.trackingRepository.findTrackingByIdAndDeletedAtIsNull(idTracking).orElseThrow(
                () -> new TrackingNotFoundException("The tracking does not exist",
                        new ExceptionsDetails(false, "The tracking you want to update does not exist", null))
        );

        BeanUtils.copyProperties(tracking, existingTracking, "id");
        this.trackingRepository.save(existingTracking);

        ServiceResponse response = new ServiceResponse();

        response.setSuccess(true);
        response.setMessage("The tracking has been updated successfully");
        response.setData(existingTracking);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> deleteTracking(String idProject, String idTracking) {
        Project existingProject = this.projectRepository.findProjectByIdAndDeletedAtIsNull(idProject).orElseThrow(
                () -> new ProjectNotFoundException("The project does not exist",
                        new ExceptionsDetails(false, "The project for which you are trying to delete the tracking does not exist", null))
        );

        Tracking existingTracking = this.trackingRepository.findTrackingByIdAndDeletedAtIsNull(idTracking).orElseThrow(
                () -> new TrackingNotFoundException("The tracking does not exists",
                        new ExceptionsDetails(false, "The tracking you are trying to delete does not exist", null))
        );

        ServiceResponse response = new ServiceResponse();
        existingTracking.setDeletedAt(LocalDate.now());
        trackingRepository.save(existingTracking);

        response.setSuccess(true);
        response.setMessage("The tracking has been successfully deleted");
        response.setData(null);

        return ResponseEntity.ok(response);
    }
}