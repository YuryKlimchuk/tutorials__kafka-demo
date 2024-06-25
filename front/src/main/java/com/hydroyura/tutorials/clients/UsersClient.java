package com.hydroyura.tutorials.clients;

import com.hydroyura.tutorials.controllers.UsersController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "client-name", url = "http://localhost:8003")
public interface UsersClient extends UsersController{

}
