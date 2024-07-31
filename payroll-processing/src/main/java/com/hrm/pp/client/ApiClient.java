package com.hrm.pp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("EMPLOYEE-SERVICE")
public interface ApiClient {
    @GetMapping("/v1/employees/exists/{id}")
    Boolean existById(@PathVariable Long id);
}
