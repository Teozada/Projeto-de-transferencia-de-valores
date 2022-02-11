package com.provadigitalrepublic.dto;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PersonDto {

    public static class Create {

        @NotNull
        public String cpf;

        @NotNull
        public String name;
    }

    public static class Transfer {

        @NotNull
        public String senderCpf;

        @NotNull
        public String receiverCpf;

        @NotNull
        public Double value;
    }

    public static class Deposit {

        @NotNull
        public String receiverCpf;

        @NotNull
        public Double value;
    }
}
