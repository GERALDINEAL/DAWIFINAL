package pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.response;

import pe.edu.cibertec.DAWI_ATOCHE_LAURENTE_GERALDINE_CONSUELO.dto.CarDetailDto;

public record FindCarByIdResponse(String code,
                                  String error,
                                  CarDetailDto car){

}


