package com.crm1.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min = 3,message = "The name is too short")
    private String name;

    @Email
    private String emailId;

    @Size(min = 10, max = 10, message = "The phone number should be 10 digits")
    private String mobile;

    private Date date;
}
