package com.metricstream.systemi.rga.dao;


/**
 * This class will initialize DBInstance object with the corresponding
 * DB impelementation specified in applicationContext-grci-config.xml
 * @author asif.u
 */
public class DBInstance {
 
 	/** The db instance. */
	 private  IDBHelper dbInstance;
 	
	 /** The instance. */
	 private static DBInstance instance=null;
 	
	 /** The lock. */
	 private static String lock="lock";
 	
 	/**
	  * Instantiates a new DB instance.
	  */
	 private DBInstance(){
 		
 	}
 	
 	/**
	  * Gets the db instance.
	  *
	  * @return the db instance
	  */
	 public  IDBHelper getDbInstance(){
 		return dbInstance;
 	}
 	
 	/**
	  * Gets the single instance of DBInstance.
	  *
	  * @return single instance of DBInstance
	  */
	 public static DBInstance getInstance(){
 		if(instance==null)
 		{
 			synchronized (lock) {
				instance=new DBInstance();
			}
 		}
 		
 		return instance;
 	}


	/**
	 * Sets the db instance.
	 *
	 * @param dbInstance the dbInstance to set
	 */
	public void setDbInstance(IDBHelper dbInstance) {
		this.dbInstance = dbInstance;
	}
 	
 	
}
