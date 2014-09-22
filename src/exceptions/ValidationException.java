/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author armel
 */
public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    
    public ValidationException(String _message ){
        super( _message );
    }
}
