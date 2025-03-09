package com.example.teachermanagement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("email")
    @NotBlank(message = "Email number is required")
    private String email;

    private String address;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @JsonProperty("retype_password")
    private String retypePassword;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @NotNull(message = "Role ID is required")
    @JsonProperty("role_id")
    private Long roleId;

}
