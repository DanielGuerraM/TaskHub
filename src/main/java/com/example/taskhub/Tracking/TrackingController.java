package com.example.taskhub.Tracking;

import com.example.taskhub.Exceptions.ProjectExceptions.ProjectException;
import com.example.taskhub.Exceptions.ProjectExceptions.ProjectNotFoundException;
import com.example.taskhub.Exceptions.TrackingExceptions.TrackingException;
import com.example.taskhub.Exceptions.TrackingExceptions.TrackingNotFoundException;
import com.example.taskhub.Exceptions.UserExceptions.UserNotFoundException;
import com.example.taskhub.Tracking.DTO.CreateTrackingDTO;
import com.example.taskhub.Tracking.DTO.UpdateTrackingDTO;
import com.example.taskhub.Util.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/tracking")
public class TrackingController {
    private final TrackingService trackingService;

    @Autowired
    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping(path = "{idProject}")
    public ResponseEntity<Object> findAllTrackingByProjectId(@PathVariable("idProject") String idProject) {
        return this.trackingService.findAllTrackingByProjectId(idProject);
    }

    @GetMapping(path = "{idProject}/news/{idTracking}")
    public ResponseEntity<Object> findTrackingById(@PathVariable("idProject") String idProject, @PathVariable("idTracking") String idTracking) throws ProjectException, ProjectNotFoundException, TrackingException, TrackingNotFoundException {
        return this.trackingService.findSingleTrackingById(idProject, idTracking);
    }

    @PostMapping
    public ResponseEntity<Object> createTracking(@RequestBody @Valid CreateTrackingDTO tracking, BindingResult result) throws ProjectNotFoundException, UserNotFoundException {
        if(result.hasErrors()) {
            ServiceResponse response = new ServiceResponse();

            response.setSuccess(false);
            response.setMessage("Validation errors");
            response.setData(result.getAllErrors());

            return ResponseEntity.badRequest().body(response);
        }

        return this.trackingService.createTracking(tracking);
    }

    @PatchMapping(path = "{idProject}/news/{idTracking}")
    public ResponseEntity<Object> updateTracking(@RequestBody @Valid UpdateTrackingDTO tracking,
                                                 @PathVariable("idProject") String idProject,
                                                 @PathVariable("idTracking") String idTracking,
                                                 BindingResult result) throws ProjectNotFoundException, TrackingNotFoundException {
        if(result.hasErrors()) {
            ServiceResponse response = new ServiceResponse();

            response.setSuccess(false);
            response.setMessage("Validation errors");
            response.setData(result.getAllErrors());

            return ResponseEntity.badRequest().body(response);
        }

        return this.trackingService.updateTracking(idProject, idTracking, tracking);
    }

    @DeleteMapping(path = "{idProject}/news/{idTracking}")
    public ResponseEntity<Object> deleteTracking(@PathVariable("idProject") String idProject, @PathVariable("idTracking") String idTracking) throws ProjectNotFoundException, TrackingNotFoundException {
        return this.trackingService.deleteTracking(idProject, idTracking);
    }
}
