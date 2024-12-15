package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto;

public record CarDto(Integer carId,
                     String make,
                     String model,
                     String color,
                     String engineType,
                     Integer year,
                     String ownerName
                     ) {
}
