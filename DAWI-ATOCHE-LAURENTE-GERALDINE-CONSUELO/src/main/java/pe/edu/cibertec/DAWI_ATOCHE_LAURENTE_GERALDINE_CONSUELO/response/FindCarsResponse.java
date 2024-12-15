package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.response;

import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.CarDto;

public record FindCarsResponse(String code,
                               String error,
                               Iterable<CarDto> cars){

}


