/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author EdgarUris
 */
public class RegexUtil {
    
    public RegexUtil(){
        
    }
    
    public boolean validaCorreoEstudiante(String correo){
        Pattern patCorreo = Pattern.compile("^[a-z]+\\.[a-z]+[1-9][0-9]{5,6}@potros\\.itson\\.edu\\.mx$");
        //nombre.apellido111111@potros.itson.edu.mx algo asi el id no puede empezar en 0, importante
        Matcher m = patCorreo.matcher(correo);
        return m.matches();
    }
    
    public boolean validaContrasena(String contrasena){
        Pattern patContra = Pattern.compile("^[a-zA-Z0-9_\\$]{8,15}$");
        //contraseña con mayusculas minisculas numeros y algunos caracteres especiales
        Matcher m = patContra.matcher(contrasena);
        return m.matches();
    }
    
    public boolean validaInteresesHobbies(String aficiones){
        Pattern patAficiones = Pattern.compile("^(?!\\,{2})([a-zA-Z0-9\\- ]+, ){1,9}([a-zA-Z0-9\\- ]+)$");
        //deberia ser algo como: "un gusto, otro gusto"
        Matcher m = patAficiones.matcher(aficiones);
        return m.matches();
    }
    
   
}
