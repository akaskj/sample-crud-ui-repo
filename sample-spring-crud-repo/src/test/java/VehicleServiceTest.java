import sample.Application;
import sample.model.Vehicle;
import sample.repository.VehicleRepository;
import sample.service.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class VehicleServiceTest {

  @Autowired
  VehicleService vehicleService;

  @Autowired
  VehicleRepository vehicleRepository;

  @Before
  public void setup() {

    Vehicle vehicle = new Vehicle();

    vehicle.setYear(2001);
    vehicle.setMake("test");
    vehicle.setModel("test");

    vehicleRepository.save(vehicle);

  }

  @Test
  public void listOfVehiclesTest() {

    ResponseEntity responseEntity = vehicleService.listOfVehicles();

    Iterable<Vehicle> vehicle = (Iterable<Vehicle>) responseEntity.getBody();

    assertThat(responseEntity.getStatusCode().toString()).isNotNull().isEqualTo("200");
    assertThat(vehicle.iterator().next().getMake()).isEqualTo("test");

  }

  @Test
  public void saveVehicleTest() {

    Vehicle vehicle = new Vehicle();

    vehicle.setYear(2001);
    vehicle.setMake("save_test");
    vehicle.setModel("save_test");

    ResponseEntity responseEntity = vehicleService.saveVehicle(vehicle);

    Vehicle savedVehicle = (Vehicle) responseEntity.getBody();

    assertThat(savedVehicle).isNotNull();
    assertThat(responseEntity.getStatusCode().toString()).isNotNull().isEqualTo("200");
    assertThat(savedVehicle.getModel()).isEqualTo(vehicle.getModel());

  }

  @Test
  public void updateVehicleTest() {

    Vehicle vehicle = new Vehicle();
    vehicle.setYear(2001);
    vehicle.setModel("test");
    vehicle.setMake("test");

    Vehicle savedVehicle = vehicleRepository.save(vehicle);

    savedVehicle.setModel("update_test");

    ResponseEntity responseEntity = vehicleService.updateVehicle(savedVehicle);

    Vehicle updatedVehicle = (Vehicle) responseEntity.getBody();

    assertThat(updatedVehicle).isNotNull();
    assertThat(responseEntity.getStatusCode().toString()).isNotNull().isEqualTo("200");
    assertThat(updatedVehicle.getModel()).isEqualTo(vehicle.getModel());

  }

}
