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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@PersistenceContext(unitName = "addressbook-ejbPU")

@Stateless
public class bean1 implements bean1Local {
EntityManager em;
    @Override
    public void insert_cust(String fname, String lname) {
        Customer c=new Customer();
        c.setFname(fname);
        c.setLname(lname);
        em.persist(c);
    
    }

    @Override
    public void update_cust(int id, String fname, String lname) {
            Customer c=(Customer)em.find(Customer.class, id);
            c.setFname(fname);
            c.setLname(lname);
            em.merge(c);
    }

    @Override
    public void remove_cust(int id) {
        Customer c=(Customer)em.find(Customer.class, id);
        em.remove(c);
    }

    @Override
    public Collection<Customer> get_cust() {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }

    @Override
    public Collection<Customer> get_cust_name(String fname) {
        Collection<Customer> c=em.createNamedQuery("Customer.findByFname").
                setParameter("fname", fname).getResultList();
        return c;
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Customer get_cust_id(int id) { 
        return em.find(Customer.class, id);
    }

    @Override
    public void insert_add(String street, String city, String state, String zip, int c_id) {
    
        Customer c=em.find(Customer.class, c_id);
        Collection<Address> address=c.getAddressCollection();
        
        Address new_address=new Address();
        new_address.setStreet(street);
        new_address.setCity(city);
        new_address.setState(state);
        new_address.setZip(zip);
        new_address.setCId(c);
        
        address.add(new_address);
        c.setAddressCollection(address);
        
        em.persist(new_address);
        em.merge(c);
        
    
    }

    @Override
    public void update_add(int a_id, String street, String city, String state, String zip, int c_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove_add(int a_id, int c_id) {
            Customer c=em.find(Customer.class,c_id);
            Address a=em.find(Address.class, a_id);
            
            Collection<Address> addresses=c.getAddressCollection();
            
            if(addresses.contains(a))
            {
                addresses.remove(a);
                c.setAddressCollection(addresses);
                em.remove(a);
            }
    }

    @Override
    public Collection<Address> get_add_cust(int c_id) {
        Customer c=em.find(Customer.class, c_id);
        return c.getAddressCollection();
    }

    @Override
    public Collection<Address> get_add_city(String city) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert_sub(String title, String type) {
        Subscription s=new Subscription();
        s.setTitle(title);
        s.setType(type);
        em.persist(s);
    }

    @Override
    public void update_sub(int s_id, String title, String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove_sub(int s_id) {
        Subscription s=(Subscription)em.find(Subscription.class, s_id);
        em.remove(s);
    }

    @Override
    public Collection<Subscription> get_sub() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add_sub_to_cust(int c_id, Collection<Integer> s_id) {
        
        Customer cust=em.find(Customer.class, c_id);
        Collection<Subscription> subscriptions=cust.getSubscriptionCollection();
        
        for(Integer s:s_id)
        {
            Subscription sub=(Subscription) em.find(Subscription.class, s);
            if(!subscriptions.contains(sub))
            {
                subscriptions.add(sub);
                Collection<Customer> customer=sub.getCustomerCollection();
                customer.add(cust);
                cust.setSubscriptionCollection(subscriptions);
                sub.setCustomerCollection(customer);
                
                em.merge(cust);
                        
            }
            
        }

    }

    @Override
    public void remove_sub_to_cust(int c_id, Collection<Integer> s_id) {

          Customer cust=em.find(Customer.class, c_id);
        Collection<Subscription> subscriptions=cust.getSubscriptionCollection();
        
        for(Integer s:s_id)
        {
            Subscription sub=(Subscription) em.find(Subscription.class, s);
            if(subscriptions.contains(sub))
            {
                subscriptions.remove(sub);
                Collection<Customer> customer=sub.getCustomerCollection();
                customer.remove(cust);
                cust.setSubscriptionCollection(subscriptions);
                sub.setCustomerCollection(customer);
                
                em.merge(cust);
                        
            }
            
        }

    }

    @Override
    public Collection<Subscription> get_sub_cust(int c_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Subscription> get_sub_type(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
