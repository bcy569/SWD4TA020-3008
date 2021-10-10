package fi.dev.academy.vaccinationdatabase.domain_class_pojo_orm;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {

        @NotEmpty
        @Size(min=4, max=30)
        private String username = "";

        @NotEmpty
        @Size(min=7, max=30)
        private String password = "";

        @NotEmpty
        @Size(min=7, max=30)
        private String passwordCheck = "";
}
