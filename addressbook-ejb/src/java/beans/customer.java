/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Customer;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ADMIN
 */
@Stateless
public class customer implements customerLocal {
@PersistenceContext(unitName = "addressbook-ejbPU")
   EntityManager em;

    @Override
    public Collection<Customer> get_cust() {
        
        return em.createNamedQuery("Customer.findAll").getResultList();
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert_cust(Customer cust) {
        em.persist(cust);
    }

    @Override
    public void delete_cust(int id) {
        Customer cust=(Customer)em.find(Customer.class, id);
        em.remove(cust);
    }


    @Override
    public void update_cust(int id, String fname, String lname) {
       Customer customer=(Customer)em.find(Customer.class,id);
       customer.setFname(fname);
       customer.setLname(lname);
       em.merge(customer);
    }
    
    

   
}
