/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islas.clinica;

/**
 *
 * @author 52222
 */
public class Paciente {
    
    String nombrePaciente;
    String id;

    public Paciente(String nombrePaciente, String id) {
        this.nombrePaciente = nombrePaciente;
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getId() {
        return id;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public void setId(String id) {
        this.id = id;
    }
  
    
}
