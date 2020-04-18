/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Address;
import entity.Customer;
import entity.Subscription;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author ADMIN
 */
@Local
public interface bean1Local {
    
    
    //Customer
    void insert_cust(String fname,String lname);
    void update_cust(int c_id,String fname,String lname);
    void remove_cust(int c_id);
    Collection<Customer> get_cust();
    Collection<Customer> get_cust_name(String fname);
    Customer get_cust_id(int c_id);
    
    
    //Address
    void insert_add(String street,String city,String state,String zip,int c_id);
    void update_add(int a_id,String street,String city,String state,String zip,int c_id);
    void remove_add(int a_id,int c_id);
    Collection<Address> get_add_cust(int c_id);
    Collection<Address> get_add_city(String city);
    
    //suscription
    
    void insert_sub(String title,String type);
    void update_sub(int s_id,String title,String type);
    void remove_sub(int s_id);
    Collection<Subscription> get_sub();
    
    void add_sub_to_cust(int c_id,Collection<Integer> s_id);
    void remove_sub_to_cust(int c_id,Collection<Integer> s_id);
    Collection<Subscription> get_sub_cust(int c_id);
    Collection<Subscription> get_sub_type(String type);
}
