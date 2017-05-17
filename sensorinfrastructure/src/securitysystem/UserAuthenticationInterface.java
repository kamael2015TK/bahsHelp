/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securitysystem;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface UserAuthenticationInterface {

    @WebMethod public boolean login(String name, String pass);


}
