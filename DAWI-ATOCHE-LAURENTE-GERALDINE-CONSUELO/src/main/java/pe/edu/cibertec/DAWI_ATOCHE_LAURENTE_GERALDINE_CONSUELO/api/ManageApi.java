package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.CarDto;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.response.*;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars(@RequestParam(value = "id", defaultValue = "0") String id){
        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDto> optional = manageService.getAllCarsById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDto carDto = optional.get();
                    return new FindCarsResponse("01", "", List.of(carDto));
                } else {
                    return new FindCarsResponse("02", "Car not found", null);
                }

            } else {
                List<CarDto> cars = manageService.getAllCars();
                if (!cars.isEmpty())
                    return new FindCarsResponse("01", "", cars);
                else
                    return new FindCarsResponse("03", "Car list not found", cars);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99", "Service not found", null);
    }
    }

    @GetMapping("/detail")
    public FindCarByIdResponse findCarById(@RequestParam(value = "id", defaultValue = "0") String id) {

        try {
            if (Integer.parseInt(id) > 0) {
                Optional<CarDetailDto> optional = manageService.getCarById(Integer.parseInt(id));
                if (optional.isPresent()) {
                    CarDetailDto carDetailDto = optional.get();
                    return new FindCarByIdResponse("01", "", carDetailDto);
                } else {
                    return new FindCarByIdResponse("02", "Car not found", null);
                }

            } else
                return new FindCarByIdResponse("03", "Parameter not found", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarByIdResponse("99", "Service not found", null);

        }

    }

    @PostMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto) {

        try {
            if (manageService.updateCar(carDto)) {
                return new UpdateCarResponse("01", "");
            } else {
                return new UpdateCarResponse("02", "Car not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "Service not found");

        }

    }

    @DeleteMapping("/delete/{id}")
    public DeleteCarByIdResponse deleteCar(@PathVariable String id) {
        try {
            if (manageService.deleteCarById(Integer.parseInt(id))) {
                return new DeleteCarByIdResponse("01", "Car deleted successfully");
            } else {
                return new DeleteCarByIdResponse("02", "Car not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarByIdResponse("99", "Service error");
        }
    }


    @PostMapping("/add")
    public AddCarResponse addCar(@RequestBody CarDetailDto carDetailDto) {
        try {
            if (manageService.addCar(carDetailDto)) {
                return new AddCarResponse("01", "Car added successfully");
            } else {
                return new AddCarResponse("02", "Car already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AddCarResponse("99", "Service error");
        }
    }

       /*
    @PostMapping("/delete/{id}")
    public DeleteCarByIdResponse deleteCar(@RequestParam String id) {
        try {
            if (manageService.deleteCarById(Integer.parseInt(id))) {
                return new DeleteCarByIdResponse("01", "Car deleted successfully");
            } else {
                return new DeleteCarByIdResponse("02", "Car not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarByIdResponse("99", "Service error");
        }
    }
*/

}
