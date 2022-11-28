/**
 * Matala 12 - This class represents a Car object
 * 
 * @author Victor Ponomarenko
 * @version 2023a
 */
public class Car
{
  private int _id;
  private char _type;
  private String _brand;
  private boolean _isManual;
  
  private final int DEFUALT_ID = 9999999; // sets the defualt id for the car
  private final char DEFUALT_TYPE = 'A'; // sets the defualt type for the car
  
  /**
   * creates a new Car object
   * Creates a new Car object
   * id should be a 7 digits number, otherwise set it to 9999999
   * type should be 'A','B','C' or 'D', otherwise set it to 'A'
   * @param id - the id of the car (7 digits number)
   * @param type - the type of the car ('A','B','C' or 'D')
   * @param brand - the car's brand
   * @param isManual - flag indicating if the car is manual
   */
  public Car (int id, char type, String brand, boolean isManual)
  {
      if(id <= 9999999 && id >= 1000000) // checking if the id valid
          _id = id;
      else
          _id = DEFUALT_ID;
      if(type == 'A' || type =='B' || type =='C' || type =='D') // checking if the type is valid.
          _type = type;
      else
          _type = DEFUALT_TYPE;
      _brand = brand;
      _isManual = isManual;
  }
  /**
   * Copy constructor
   * @param other - the car to be copied
   */
  public Car(Car other)
  {
      this._id = other._id;
      this._type = other._type;
      this._brand = other._brand;
      this._isManual = other._isManual;
  }
  /**
   * Gets the id
   * @return the id
   */
  public int getId()
  {
      return _id;
  }
  /**
   * Gets the type
   * @return the type
   */
  public char getType()
  {
      return _type;
  }
  /**
   * Gets the brand
   * @return the brand
   */
  public String getBrand()
  {
      return _brand;
  }
  /**
   * Gets the isManual flag
   * @return isManual flag
   */
  public boolean getIsManual()
  {
      return _isManual;
  }
  /**
   * Sets the id (only if the given id is valid)
   * @param id the value to be set
   */
  public void setId(int id)
  {
      if(id <= 9999999 && id >= 1000000)
          this._id = id;
      else
          _id = _id;
  }
  /**
   * Sets the type (only if the given type is valid)
   * @param type the value to be set
   */
  public void setType(char type)
  {
      if(type == 'A' || type =='B' || type =='C' || type =='D')
          this._type = type;
      else
          _type = _type;
  }
  /**
   * Sets the brand of the car
   * @param brand - the brand to be set
   */
  public void setBrand(String brand)
  {
      this._brand= brand;
  }
  /**
   * Sets the isManual flag of the car 
   * @param manual the flag to be set
   */
  public void setIsManual(boolean manual)
  {
      this._isManual = manual;
  }
  /**
   * Returns a String object that represents this car 
   * @return String represents this car in the following format
   * id:1234567 type:B brand:Toyota gear:manual (or auto)
   */
  public String toString()
  {
      if(_isManual == true) // matching the relevat string to the gear of the car.
          return ("id:"+_id+ " type:"+_type+" brand:"+_brand+" gear:manual");
      return ("id:"+_id+ " type:"+_type+" brand:"+_brand+" gear:auto");
  }
  /**
   * Check if two cars are the same
   * Cars are considered the same if they have the same type, brand and gear
   * @param other - the car to compare this car to
   * @return - true if the cars are the same, otherwise false
   */
  public boolean equals (Car other) // checking if two car objects are equal.
  {
      if(this._type == other._type && this._brand == other._brand && this._isManual == other._isManual)
          return true;
      return false;
  }
  /**
   * Check if this car is better than the other car
   * A car is considered better than another car if its type is higher
   * If both cars have the same type, an automated car is better than a manual car.
   * @param other - car to compare this car to
   * @return - true if this car is better than the other car, otherwise false
   */
  public boolean better (Car other) // checking if one car better then the other.
  {
      if(this._type > other._type)
          return true;
      else if(this._type == other._type) // checking the case of same type of type/
          if(this._isManual == false && other._isManual == true)
              return true;
      return false;
  }
  /**
   * Check if this car is worse than the other car
   * @param other - car to compare this car to
   * @return - true if this car is worse than the other car, otherwise false
   */
  public boolean worse (Car other) // cheking if car worse then other on the base of better method.
  {
      if(other.better(this))
          return true;
      return false;
  }
}
