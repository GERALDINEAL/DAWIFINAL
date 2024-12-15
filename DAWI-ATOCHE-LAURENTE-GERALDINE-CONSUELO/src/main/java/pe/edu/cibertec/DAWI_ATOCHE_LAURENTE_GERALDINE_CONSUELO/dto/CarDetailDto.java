package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto;

import java.util.Date;

public record CarDetailDto(Integer carId,
                           String make,
                           String model,
                           String color,
                           String engineType,
                           Integer year,
                           String ownerName,
                           String vin,
                           String licensePlate,
                           String ownerContact,
                           Date purchaseDate,
                           Integer mileage,
                           String insuranceCompany,
                           String insurancePolicyNumber,
                           Date registrationExpirationDate,
                           Date serviceDueDate
) {
}
