/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islas.clinica;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Iterator;
import java.util.HashMap;
/**
 *
 * @author Ivan Islas
 */
public class Clinica {

    /**
     * @param args the command line arguments
     */
    public static Scanner leer = new Scanner(System.in);
    public static String outputDoctores = "C:\\Users\\52222\\Documents\\NetBeansProjects\\Clinica\\src\\main\\java\\islas\\clinica\\Doctores.csv";
    public static String outputPacientes = "C:\\Users\\52222\\Documents\\NetBeansProjects\\Clinica\\src\\main\\java\\islas\\clinica\\Pacientes.csv";
    public static String outputCitas = "C:\\Users\\52222\\Documents\\NetBeansProjects\\Clinica\\src\\main\\java\\islas\\clinica\\Citas.csv";
    public static String outputAdministradores = "C:\\Users\\52222\\Documents\\NetBeansProjects\\Clinica\\src\\main\\java\\islas\\clinica\\Administradores.csv";

    public static ArrayList <Doctor> aDoc = new ArrayList<Doctor>();
    public static ArrayList <Paciente> aPac = new ArrayList<Paciente>(); 
    
    
    public static void main(String[] args) throws IOException, ParseException{
        


            BufferedWriter bwDoctores = new BufferedWriter(new FileWriter(outputDoctores, true));
            BufferedWriter bwPacientes = new BufferedWriter(new FileWriter(outputPacientes, true));
            BufferedWriter bwCitas = new BufferedWriter(new FileWriter(outputCitas, true));
            BufferedWriter bwAdmin = new BufferedWriter(new FileWriter(outputAdministradores, true));

            String line;

            leer.useDelimiter("\n");
            int opc, ban = 0, ban1 = 0;
            boolean acceso = false;
            
            HashMap<String, String> mapa = new HashMap<String, String>();  // hashmap para contener administradores
            
            System.out.println("** Bienvenido al sistema de Clinica **\n");
            
            do{     // bucle do-while para verificar que el usuario sea un administrador del sistema
                ban1 = archivV("C:\\Users\\52222\\Documents\\NetBeansProjects\\Clinica\\src\\main\\java\\islas\\clinica\\Administradores.csv"); //llamado a metodo que rectifica que el archivo no esté vacio
                if(ban1 == 1)
                {
                    System.out.println("***Ingreso para administradores***\n");
                    System.out.println("Ingrese su ID: ");
                    String id = leer.next();
                    System.out.println("Ingrese su contraseña: ");
                    String pass = leer.next();
                    
                    load(mapa);  // metodo que carga los datos del archivo a un hashmap

                    acceso = contrasena(mapa,id,pass);  //la variable acceso recibe el valor que devuelve el metodo que evalúa el id y la contraseña

                }
                else            /// si el archivo de administradores esta vacío se da de alta uno por default
                {    
                    System.out.println("No hay Administradores dados de alta");
                    System.out.println("** Alta de Administrador **\n");
                    System.out.println("Ingrese su ID: ");
                    String id = leer.next();
                    System.out.println("Ingrese su contraseña: ");
                    String pass = leer.next();                    

                    creaAdministrador(mapa, id, pass, bwAdmin); // llamado a metodo que da de alta administrador y lo guarda en el archivo

                }
                
            }while ( acceso == false ); // el ciclo continúa hasta que la contraseña y id sean correctos
            
                do          // ciclo do while para control del menu, es controlado con la variable ban
                {
                    try{                                                                    //Try para iniciar excepcion de errores

                    System.out.println("\nSeleccione la opción deseada:\n");                // menu de sisttema
                        System.out.println("Alta de Administrador  -------  [1]");
                        System.out.println("Alta de Doctor ---------------  [2]");
                        System.out.println("Alata Paciente ---------------  [3]");
                        System.out.println("Agendar Cita  ----------------  [4]");
                        System.out.println("Verificar citas --------------  [5]");

                        System.out.println("Salida  ------------------------  [0]");

                        opc = leer.nextInt();

                        /// Menu de opciones
                        switch(opc)
                        {
                            case 1:

                                System.out.println("** Alta de Administrador **\n");
                                System.out.println("Ingrese su ID: ");
                                String id = leer.next();
                                System.out.println("Ingrese su contraseña: ");
                                String pass = leer.next();                    

                                creaAdministrador(mapa, id, pass, bwAdmin); // llamado a metodo que da de alta administrador y lo guarda en el archivo       

                                break;

                            case 2:


                                creaDoctor(bwDoctores);
                                break;

                            case 3:

                                creaPaciente(bwPacientes);

                                break;

                            case 4:  

                                creaCita(bwCitas);

                                break;
                            case 5:

                                break;

                            case 0:

                                System.out.println("Saliendo");     // opcion de salida del programa
                                ban = 1;                            // se cambia el valor de la bandera que controla el ciclo do-while del menu
                                break;
                            default:
                                System.out.println("Opción incorrecta\n");  //default que muestra un mensaje de error al introducir una opcion incorrecta
                                break;


                        }
                    }
                    catch (Exception e)             // catch que obtiene el error y nos muestra un mensaje de error en caso de que se presente uno
                    {
                        System.out.println("ERROR !!\n");
                        break;

                    }
                }while(ban == 0);
        
    }
    
////
/// Metodo para crear un doctor y guardarlo en el archivo Doctores
////    
        public static void creaDoctor(BufferedWriter bw) throws IOException {
            
            
        System.out.print("Ingresa el nombre del doctor\n");         /// Se piden los datos del objeto
        String nombreDoctor = leer.next();
        System.out.print("Ingresa la especialidad del doctor\n");
        String especialidadDoctor = leer.next();
        System.out.print("Ingresa el Id del doctor\n");
        String idDoctor = leer.next();
        Doctor doctorInfo = new Doctor(idDoctor, nombreDoctor, especialidadDoctor); // se crea el objeto doctor con los datos ingresados por usuario

        
        try(FileWriter fw = new FileWriter(outputDoctores, true);  // se crean variables auxiliares 
        BufferedWriter bww = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bww)){


                    out.print(doctorInfo.id);           // se escriben los datos en el archivo
                    out.print(",");
                    out.print(doctorInfo.nombreDoctor);
                    out.print(",");
                    out.println(doctorInfo.esp);

                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }

    }
    public static void creaPaciente(BufferedWriter bw) throws IOException {

        
        System.out.print("Ingresa el nombre del Paciente\n");         /// Se piden los datos del objeto
        String nombrePaciente = leer.next();
        System.out.print("Ingresa el Id del paciente\n");
        String idPaciente = leer.next();
        Paciente PacienteInfo = new Paciente(nombrePaciente,idPaciente); // se crea el objeto doctor con los datos ingresados por usuario

        
        try(FileWriter fw = new FileWriter(outputPacientes, true);  // se crean variables auxiliares 
        BufferedWriter bww = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bww)){


                    out.print(PacienteInfo.id);           // se escriben los datos en el archivo
                    out.print(",");
                    out.println(PacienteInfo.nombrePaciente);


                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }

    }
    public static void creaCita(BufferedWriter bw) throws IOException {
        
            try{
                System.out.println("Ingrese el id de la cita:");
                int idC = leer.nextInt();
                System.out.print("Ingresa la fecha de tu cita\n");
                String fechaCitaString = leer.next();
                Date fechaCita = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCitaString);
                System.out.print("Ingresa el motivo de tu cita\n");
                leer.nextLine();
                String motivoCita = leer.nextLine();
                System.out.print("Ingresa el id del doctor\n");
                String idDoctor = leer.next();
                System.out.print("Ingresa el id del paciente\n");
                String idPaciente = leer.next();
                Cita citaInfo = new Cita(idC, fechaCita, Integer.parseInt(idDoctor), Integer.parseInt(idPaciente), motivoCita);

        
        try(FileWriter fw = new FileWriter(outputCitas, true);  // se crean variables auxiliares 
        BufferedWriter bww = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bww)){


                    out.print(citaInfo.id);           // se escriben los datos en el archivo
                    out.print(",");
                    out.println(citaInfo.fechaCita);
                    out.print(",");
                    out.print(citaInfo.idDoctor);
                    out.print(",");
                    out.print(citaInfo.idPaciente);
                    out.print(",");
                    out.println(citaInfo.motivoCita);



                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }
                
                
            }
                catch(ParseException e) {
                    System.out.println("ParseException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                            }
            
                /*try(FileWriter fw = new FileWriter(outputCitas, true);  // se crean variables auxiliares 
                    BufferedWriter bww = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bww)){
                    
                    
                    System.out.println("Ingrese el id de la cita: ");
                    int idcita = leer.nextInt();
                    System.out.print("Ingresa el motivo de tu cita\n");
                    String motivoCita = leer.next();
                    loadPaciente(aPac);                    
                    
                    System.out.print("Ingresa el id del paciente\n");
                    String idPaciente = leer.next();

                    for(int i=0; i <aPac.size(); i++)
                    {
                        if(aPac.get(i).getId().equals(idPaciente))
                        {
                            //Paciente pacientePC = new Paciente(aPac.get(i).getId(),aPac.get(i).getNombrePaciente());
                            String auxID = aPac.get(i).getId();
                            String auxnomP = aPac.get(i).getNombrePaciente();
                            
                            System.out.println(auxID);
                        }
                        
                    }
                    
                    Paciente pacientePC = new Paciente(auxID,auxnomP);
                    System.out.print("Ingresa el id del doctor\n");
                    String idDoctor = leer.next();
                    
                    System.out.print("Ingresa la fecha de tu cita\n");
                    String fechaCitaString = leer.next();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date fechaCita = formato.parse(fechaCitaString);


                    Cita citaInfo = new Cita(idcita, fechaCita, motivoCita);

                    citaInfo.asignarP(pacientePC);
                    
                    
                    out.print(citaInfo.id);           // se escriben los datos en el archivo
                    out.print(",");
                    out.println(citaInfo.fechaCita.toString());
                    out.print(",");
                    out.print(citaInfo.motivoCita);
                    out.print(",");
                    out.print(citaInfo.idCita);
                    out.print(",");
                    out.print(citaInfo.idPaciente);
                    out.println(",");


                    }
                 catch(ParseException e) {
                    System.out.println("ParseException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bw != null) {
                                bw.close();
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }*/
                    

    }
    
    public static int archivV(String archivo)
    {
        int i = 0;
        
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(archivo));     
            if (br.readLine() == null) 
            {
                System.out.println("file empty");
            }
            
            else i = 1;
        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        }
        return i;
    }
    
    
    /////////////////////
    ////////////////////
    //Metodo para crear y guardar administrador
    ////////////////////
    ////////////////////
    public static void creaAdministrador(HashMap <String,String> mapa, String id, String pw, BufferedWriter bw)throws IOException{
        
    
        if(mapa.containsKey(id))
            {
                System.out.println("\nError!\nNo se puede registrar dos veces el mismo Administrador\n"); // mensaje si el administrador ya existe
            }

        else{   
            
            mapa.put(id, pw);  //si no existe se crea el administrador
                System.out.println("\nAdministrador agregado");
            }   
        
            Iterator<String> iterator = mapa.keySet().iterator();
                String inputFilename = "C:\\Users\\52222\\Documents\\NetBeansProjects\\Clinica\\src\\main\\java\\islas\\clinica\\Administradores.csv"; 

                BufferedWriter bufferedWriter = null;

                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));

                   while(iterator.hasNext())                                //en un ciclo recorremos el hashmap
                    {
                    String llave = iterator.next();                      // el iterador se llama llave que esta funcionando como referencia en el key del hashmap


                    bufferedWriter.write(llave+","+mapa.get(llave)+"\n");   // Se escribe el hashmap en el archivo input
                    }

                    }
                 catch(IOException e) {
                    System.out.println("IOException catched while writing: " + e.getMessage());
                    } finally {
                        try {
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                                System.out.println("\nCambios guardados");
                            }
                        } catch (IOException e) {
                            System.out.println("IOException catched while closing: " + e.getMessage());
                        }
                    }
        
    }
   
    
    ///////////////
    //Metodo para validar contraseña
    //////////////
   public static boolean contrasena(HashMap <String,String> mapa, String id, String pw)
    {
       
       boolean i ;

            if(mapa.containsKey(id))
            {
                if(mapa.get(id).equals(pw))
                i = true;
                
                else
                {
                    System.out.println("La contraseña es incorrecta!\n");
                
                    i = false;
                }
            }
       
            else
            {
                System.out.println("El administrador no existe!\n");
                i = false;
            }
    
        return i;
    }
   
/////
//Metodo para cargar Hashmap que controla los auxiliares desde archivo csv
////
   public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\52222\\Documents\\NetBeansProjects\\Clinica\\src\\main\\java\\islas\\clinica\\Administradores.csv";
        String a [];                /// array auxiliar
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        // se llena el array con los datos separados por la ","
                m.put(a[0],a[1]);           // se llena el hashmap con los datos del array 
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

    }
   
   /////
   //Metodo para mostrar pacientes
   ////
   public static void loadPaciente(ArrayList Pac)
   {
        String inputFilename = outputPacientes;
        String a [];                /// array auxiliar
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        // se llena el array con los datos separados por la ","
                Pac.add(a[0]);           // se llena el arraylist con los datos del array 
                Pac.add(a[1]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }      
   }
   
   public static void loadDoctor(ArrayList Doc)
   {
        String inputFilename = outputDoctores;
        String a [];                /// array auxiliar
        
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        // se llena el array con los datos separados por la ","
                Doc.add(a[0]);           // se llena el arraylist con los datos del array 
                Doc.add(a[1]);
                Doc.add(a[2]);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }      
   }
   
}
