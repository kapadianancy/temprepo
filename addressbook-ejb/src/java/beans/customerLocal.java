/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Customer;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author ADMIN
 */
@Local
public interface customerLocal {
    public Collection<Customer> get_cust(); 
    
    public void insert_cust(Customer cust);
    
    public void delete_cust(int id);
    
    public void update_cust(int id,String fname,String lname);
}
