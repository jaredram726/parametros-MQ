package com.checkbox.checkbox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author 
 *Clase que contiene los campos para el usuario, la póliza y las casillas de verificación
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPolicy {
    private String userId;
    private String policyNumber;
    private boolean checkbox1;
    private boolean checkbox2;
    private boolean checkbox3;

}
