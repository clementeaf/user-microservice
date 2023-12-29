package com.user.usermanagementservice.DTOs;

import lombok.Data;

//Clase que devuelve la información con el token y el tipo que tenga este
@Data
public class DtoAuthRespuesta {
    private String accessToken;
    private String tokenType = "Bearer ";

    public DtoAuthRespuesta(String accessToken){
        this.accessToken = accessToken;
    }
}
