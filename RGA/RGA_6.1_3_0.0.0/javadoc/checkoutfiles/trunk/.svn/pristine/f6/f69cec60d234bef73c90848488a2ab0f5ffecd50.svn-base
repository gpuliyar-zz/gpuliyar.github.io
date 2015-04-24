package com.metricstream.systemi.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Enum PersistenceManager. Singleton class to hold the DB instance for JPA
 * @author asif.u
 */
public enum PersistenceManager {

	/** The instance. */
	INSTANCE;
	

	  /**
  	 * Gets the em factory.
  	 *
  	 * @return the emFactory
  	 */
	public EntityManagerFactory getEmFactory() {
		return emFactory;
	}

	/**
	 * Sets the em factory.
	 *
	 * @param emFactory the emFactory to set
	 */
	public void setEmFactory(EntityManagerFactory emFactory) {
		this.emFactory = emFactory;
	}

	/** The em factory. */
  	private EntityManagerFactory emFactory;
	 
	  /**
  	 * Instantiates a new persistence manager.
  	 */
  	private PersistenceManager() {
	    emFactory = Persistence.createEntityManagerFactory("rgajapcon");
	  }
	 
	  /**
  	 * Gets the entity manager.
  	 *
  	 * @return the entity manager
  	 */
  	public EntityManager getEntityManager() {
	    return emFactory.createEntityManager();
	  }
	 
	  /**
  	 * Close.
  	 */
  	public void close() {
  		try{
	    emFactory.close();
  		}catch(Exception ex){
  			
  		}
	  }
  	
  	/**
	   * Colse em.
	   *
	   * @param em the em
	   */
	  public void closeEM(EntityManager em){
  		try{
  			em.close();
  		}catch(Exception ex){
  		}
  		
  	}
}
