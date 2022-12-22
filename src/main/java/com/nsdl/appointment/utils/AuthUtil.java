package com.nsdl.appointment.utils;

import org.springframework.stereotype.Component;
import com.nsdl.appointment.dto.MainRequestDTO;
import com.nsdl.appointment.dto.MainResponseDTO;

@Component
public class AuthUtil {
	
	public static MainResponseDTO<?> getMainResponseDto(MainRequestDTO<?> mainRequestDto ){
		MainResponseDTO<?> response=new MainResponseDTO<>();
		if(mainRequestDto.getRequest()==null) {
			return response;
		}
		response.setId(mainRequestDto.getId());
		response.setVersion(mainRequestDto.getVersion());
		//response.setStatus(false);
		
		return response;
	}

}
