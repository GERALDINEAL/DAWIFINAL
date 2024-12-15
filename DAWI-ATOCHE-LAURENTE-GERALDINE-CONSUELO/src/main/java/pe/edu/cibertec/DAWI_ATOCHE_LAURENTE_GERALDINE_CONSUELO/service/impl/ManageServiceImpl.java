package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.CarDto;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.entity.Car;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.repository.CarRepository;
import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.service.ManageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {

        List<CarDto> cars = new ArrayList<CarDto>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            CarDto carDto = new CarDto(car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getColor(),
                    car.getEngineType(),
                    car.getYear(),
                    car.getOwnerName());
            cars.add(carDto);
        });
        return cars;
    }

    @Override
    public Optional<CarDto> getAllCarsById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getColor(),
                car.getEngineType(),
                car.getYear(),
                car.getOwnerName()));
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDetailDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getColor(),
                car.getEngineType(),
                car.getYear(),
                car.getOwnerName(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDto.carId());
        return optional.map(car -> {
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setColor(carDto.color());
            car.setEngineType(carDto.engineType());
            car.setYear(carDto.year());
            car.setOwnerName(carDto.ownerName());
            carRepository.save(car);
            return true;
        }).orElse(false);

    }



    @Override
    public boolean deleteCarById(int id) throws Exception {

       Optional<Car> optional = carRepository.findById(id);
       return optional.map(car -> {
           carRepository.delete(car);
           return true;
       }).orElse(false);
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDetailDto.carId());

        if (optional.isPresent())
            return false;
        else {
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setColor(carDetailDto.color());
            car.setEngineType(carDetailDto.engineType());
            car.setYear(carDetailDto.year());
            car.setOwnerName(carDetailDto.ownerName());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.licensePlate());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(carDetailDto.purchaseDate());
            car.setMileage(carDetailDto.mileage());
            car.setInsuranceCompany(carDetailDto.insuranceCompany());
            car.setInsurancePolicyNumber(carDetailDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carDetailDto.registrationExpirationDate());
            car.setServiceDueDate(carDetailDto.serviceDueDate());
            carRepository.save(car);
            return true;
    }
}


}
