/**
 * Matala 12 - This class represents a Rent object
 * 
 * @author Victor Ponomarenko
 * @version 2023a
 */
public class Rent
{
  private String _name;
  private Car _car;
  private Date _pickDate;
  private Date _returnDate;
  
  private final int LVL_A = 100, LVL_B = 150,LVL_C = 180, LVL_D = 240; // the price of each car lv.
  private final int LVL_A_DIS =90, LVL_B_DIS = 135,LVL_C_DIS = 162, LVL_D_DIS = 216; // the price of each car lv after discount.
  private final int FULL_WEEK = 7;
  
  /**
   * Creates a new Rent object
   * The return date must be at least one day after the pickup date, otherwise set it to one day after the pick up date.
   * @param name - the name of the client
   * @param car - the rented car
   * @param pick -  the pickup day 
   * @param ret - the return day
   */
  public Rent(String name, Car car, Date pick, Date ret)
  {
      _name = name;
      _car =  new Car(car);
      if(ret.after(pick.tomorrow())==true) // checking if the return date is realy after the pick date.
      {
          _pickDate = new Date (pick);
          _returnDate = new Date (ret);
      }
      else // if its not the case replace the return date for tomorrow automaticlly
      {
          _pickDate = new Date (pick);
          _returnDate =  new Date (pick.tomorrow());
      }
  }
  /**
   * Copy constructor
   * @param other - the Rent to be copied
   */
  public Rent (Rent other)
  {
      this._name = other._name;
      this._car = other._car;
      this._pickDate = other._pickDate;
      this._returnDate = other._returnDate;
  }
  /**
   * Gets the car
   * @return the car
   */
  public Car getCar()
  {
      return  new Car(_car);
  }
  /**
   * Gets the name
   * @return the name
   */
  public String getName()
  {
      return _name;
  }
  /**
   * Gets the pick up date
   * @return the pick up date
   */
  public Date getPickDate()
  {
      return new Date (_pickDate);
  }
  /**
   * Gets the return date
   * @return the return date
   */
  public Date getReturnDate()
  {
      return new Date(_returnDate);
  }
  /**
   * Sets the rented car
   * @param car - the car value to be set
   */
  public void setCar(Car car)
  {
      _car = new Car(car);
  }
  /**
   * Sets the client name
   * @param car - the name value to be set
   */
  public void setName(String name)
  {
      _name = name;
  }
  /**
   * Sets the pick up date
   * The pick up date must be at least one day before the return date, otherwise - don't change the pick up date   
   * @param pickDate - the pick up date value to be set
   */
  public void setPickDate(Date pickDate)
  {
      _pickDate = new Date (pickDate);
  }
  /**
   * Sets the return date
   * The return date must be at least one day after the pick up date, otherwise - don't change the return date   
   * @param returnDate - the return date value to be set
   */
  public void setReturnDate(Date returnDate)
  {
      _returnDate = new Date (returnDate);
  }
  /**
   * Check if 2 rents are the same 
   * @param other - date to compare this date to
   * @return - true if the rents are the same, otherwise false
   */
  public boolean equals (Rent other) // checking if the rent obj are eqyuals.
  {
      return ((this._name ==other._name && this._pickDate.equals(other._pickDate) && this._returnDate.equals(other._returnDate) && this._car.equals(other._car) == true));
  }
  /**
   * @Returns the number of rent days
   */
  public int howManyDays() // calculating the difference between pick up date and the return.
  {
      return _pickDate.difference(_returnDate);
  }
  /**
   * Gets the rent total price
   * @Returns the rent total price
   */
  public int getPrice() // calculating the totalt rent price.
  {
      if(_car.getType() == 'A') // calculating the price of car in type A
      {
          if(howManyDays() % FULL_WEEK == 0 ) // calculating the option of full discount.
          {
              return (howManyDays() * LVL_A_DIS);
          }
          else if ((howManyDays() / FULL_WEEK > 0)) // calculating the option of part discount.
                return ((howManyDays() / FULL_WEEK) * (FULL_WEEK *LVL_A_DIS)) + ((howManyDays() % FULL_WEEK)*LVL_D);
          return (howManyDays() *LVL_A);
      }
      if(_car.getType() == 'B') // calculating the price of car in type B
      {
          if(howManyDays() % FULL_WEEK == 0 )
          {
              return (howManyDays() * LVL_B_DIS); // calculating the option of full discount.
          }
          else if ((howManyDays() / FULL_WEEK > 0)) // calculating the option of part discount.
                return ((howManyDays() / FULL_WEEK) * (FULL_WEEK *LVL_B_DIS)) + ((howManyDays() % FULL_WEEK)*LVL_D);
         return (howManyDays() *LVL_B);
      }
      if(_car.getType() == 'C') // calculating the price of car in type C
      {
          if(howManyDays() % FULL_WEEK == 0 ) // calculating the option of full discount.
          {
              return (howManyDays() * LVL_C_DIS);
          }
          else if ((howManyDays() / FULL_WEEK > 0)) // calculating the option of part discount.
                return ((howManyDays() / FULL_WEEK) * (FULL_WEEK *LVL_C_DIS)) + ((howManyDays() % FULL_WEEK)*LVL_D);
          return (howManyDays() *LVL_C);
      }
      else  if(howManyDays() % FULL_WEEK == 0 ) // calculating the option of full discount.
          {
              return (howManyDays() * LVL_D_DIS);
          } 
      return ((howManyDays() / FULL_WEEK) * (FULL_WEEK *LVL_D_DIS)) + ((howManyDays() % FULL_WEEK)*LVL_D); // making type D as defualt case.
  }
  /**
   * Try to upgrade the car to a better car
   * If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
   * @param newCar - other car to compare
   * @return - the upgrade additional cost, otherwise - don't upgrade
   */
  public int upgrade (Car newCar) // checking if the car can be upgraded and and cost of the upgarde.
  {
      int sum1,sum2; // two integers to remember the sum of each car.
      sum1=getPrice();
      if(newCar.better(_car)) // if the car is better, replacing the info of the car object.
      {    
          _car.setId(newCar.getId());
          _car.setType(newCar.getType());
          _car.setBrand(newCar.getBrand());
          _car.setIsManual(newCar.getIsManual());
          sum2=getPrice();
          return sum2-sum1; // calculating the additional cost.
      }
      return 0;
  }
  /**
   * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days
   * If there is - return a new rent object with the unified dates, otherwise - return null. 
   * @param other - other Rent to compare
   * @return - a new rent object with the unified dates, otherwise - return null.
   */
  public Rent overlap (Rent other) // checking if there is an ovelap and if there is changing the dates.
  {
      Rent rFlag = new Rent (other); // using the copy constructor to make new rent object to make on him changes without changing the original.
      if(this._name == other._name && this._car.equals(other._car)) // checking if the cars data is the same
      {
          if(this._returnDate.after(other._pickDate)) 
          {
              rFlag.setPickDate(this._pickDate);
              rFlag.setReturnDate(other._returnDate);
              return rFlag;
          } 
          return null;    
      }
      return null;
  }
   /**
   * Returns a String that represents this rent.
   * @return String that represents the Rent information.
   */
  public String toString ()
  {
      return "Name:"+_name+" From:"+_pickDate+" To:"+_returnDate+" Type:"+_car.getType()+" Days:"+howManyDays()+" Price:"+getPrice();
  }
}
