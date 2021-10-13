/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islas.clinica;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author 52222
 */
public class Cita {
    
    int id;
    Date fechaCita;
    int idDoctor;
    int idPaciente;
    String motivoCita;

    public Cita (int idC, Date fecha, int doctorId, int pacienteId, String motivo){
        id = idC;
        fechaCita = fecha;
        idDoctor = doctorId;
        idPaciente = pacienteId;
        motivoCita = motivo;
    }

     
}
