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
public class Doctor {
     String id;
     String nombreDoctor;
     String esp;

    public Doctor(String id, String nombreDoctor, String esp) {
        this.id = id;
        this.nombreDoctor = nombreDoctor;
        this.esp = esp;
    }

    public String getId() {
        return id;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public String getEsp() {
        return esp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }
   
}
