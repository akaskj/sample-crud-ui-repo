import sample.Application;
import sample.model.Vehicle;
import sample.repository.VehicleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class VehicleRepositoryTest {

  @Autowired
  private VehicleRepository vehicleRepository;

  @Before
  public void setup() {

    Vehicle vehicle = new Vehicle();

    vehicle.setYear(2001);
    vehicle.setMake("test");
    vehicle.setModel("test");

    vehicleRepository.save(vehicle);

  }

  @Test
  public void findByIdTest() {

    Vehicle vehicle1 = vehicleRepository.findOne(1);

    System.out.println(vehicle1.getMake());

    assertThat(vehicle1.getModel().toString()).isEqualTo("test");

  }

  @Test
  public void createVehicleTest() {

    Vehicle vehicle = new Vehicle();

    vehicle.setYear(2001);
    vehicle.setMake("create_test");
    vehicle.setModel("create_test");

    Vehicle savedVehicle = vehicleRepository.save(vehicle);

    assertThat(savedVehicle).isNotNull();
    assertThat(savedVehicle.getModel()).isEqualTo(vehicle.getModel());

  }

  @Test
  public void updateVehicleTest() {

    Vehicle vehicle = new Vehicle();
    vehicle.setYear(2001);
    vehicle.setModel("update_test");
    vehicle.setMake("update_test");

    Vehicle originalVehicle = new Vehicle();
    originalVehicle.setModel(vehicle.getModel());

    Vehicle savedVehicle = vehicleRepository.save(vehicle);

    assertThat(vehicleRepository.findOne(savedVehicle.getId())).isNotNull();

    savedVehicle.setModel("update_model");

    Vehicle updatedVehicle = vehicleRepository.save(savedVehicle);

    assertThat(updatedVehicle).isNotNull();
    assertThat(updatedVehicle.getModel()).isEqualTo(vehicle.getModel());
    assertThat(updatedVehicle.getModel()).isNotEqualTo(originalVehicle.getModel());
    assertThat(updatedVehicle.getId()).isEqualTo(savedVehicle.getId());

  }

  @Test
  public void deleteVehicleTEst() {
    Vehicle vehicle = new Vehicle();
    vehicle.setYear(2001);
    vehicle.setModel("delete_test");
    vehicle.setMake("delete_test");

    Vehicle savedVehicle = vehicleRepository.save(vehicle);

    assertThat(vehicleRepository.findOne(savedVehicle.getId())).isNotNull();

    vehicleRepository.delete(savedVehicle.getId());
    assertThat(vehicleRepository.findOne(savedVehicle.getId())).isNull();

  }

}
