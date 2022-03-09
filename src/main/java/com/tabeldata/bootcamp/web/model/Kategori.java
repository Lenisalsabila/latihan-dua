package com.tabeldata.bootcamp.web.model;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Kategori {
    @NotNull
    private Integer category_id;
    @NotNull
    private Integer department_id;

    @NotEmpty(message = "gak boleh kosong")
    @Length(min = 4,message = "Panjang Minimal 4")
    private String name;

    @NotEmpty
    @Length(min = 6,message = "Panjang Minimal 6")
    private String description;
}
