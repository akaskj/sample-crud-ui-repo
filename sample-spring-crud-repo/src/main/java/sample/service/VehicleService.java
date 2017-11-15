package sample.service;

import sample.model.Vehicle;
import sample.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Vehicle>> listOfVehicles() {

        Iterable<Vehicle> vehicleList = null;

        try {
            vehicleList = vehicleRepository.findAll();
        }
        catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.badRequest().body("Error retrieving list of vehicles");
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(vehicleList);


    }

    @RequestMapping(value = "/vehicles", method = RequestMethod.POST)
    public ResponseEntity saveVehicle(@RequestBody Vehicle vehicle) {

        Vehicle savedVehicle;
        try {
            savedVehicle = vehicleRepository.save(vehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving vehicle data");
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(savedVehicle);

    }

    @RequestMapping(value = "/vehicles", method = RequestMethod.PUT)
    public ResponseEntity updateVehicle(@RequestBody Vehicle vehicle) {

        Vehicle updatedVehicle;
        try {
            updatedVehicle = vehicleRepository.save(vehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating vehicle data");
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(updatedVehicle);
    }

}
