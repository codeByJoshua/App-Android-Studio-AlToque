package com.example.altoque.Secundarios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class diferenciaDias {

    public static long calcularDias(LocalDate fechaInicio, LocalDate fechaFin) {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

}
