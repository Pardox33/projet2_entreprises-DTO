package com.nadia.entreprises.dto;

import java.util.Date;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntreprisesDTO {
    private Long idEnt;
    private String nomEnt;
    private Double chiffreAff;
    private Date dateCre;
    private String email;
    private SecteurDTO secteur;
    private String nomSec;
}
