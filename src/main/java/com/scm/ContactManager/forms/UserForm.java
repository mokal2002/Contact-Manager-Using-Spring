package com.scm.ContactManager.forms;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Username is required.")
    @Size(min=3,message = "Minimum Three characters required.")
    private String name;
    @Email(message = "Email is required.")
    @NotBlank
    private String email;
    @NotBlank(message = "Password is required.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,20}$",
            message = "Password must be 8-20 characters long, and include at least one uppercase letter, one lowercase letter, one digit, and one special character."
    )
    private String password;
    @NotBlank(message = "Phone number is required.")
    @Size(min=10, message = "Invalid phone number.")
    private String phoneNumber;
    @NotBlank(message = "Field is required.")
    private String about;

}
