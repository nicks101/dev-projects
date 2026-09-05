package com.nikki.taskmanager.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank(message = "Title is mandatory")
        @Size(max = 100, message = "Title can be upto 100 characters")
        String title,

        @Size(max = 200, message = "Description can be upto 200 characters")
        String description,
        Boolean completed
) {

}
