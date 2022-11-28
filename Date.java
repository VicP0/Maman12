/**
 * Matala 12 - This class represents a Date object
 * 
 * @author Victor Ponomarenko
 * @version 2023a
 */
public class Date
{
  private int _day;
  private int _month;
  private int _year; 
  
  private final int DEFUALT_DAY = 1, DEFUALT_MONTH = 1, DEFUALT_YEAR = 2000 ; // the defualt date to set in case of wrong date.
  private final int FEB_MONTH = 2, JUL_MONTH = 7, AUG_MONTH = 8, DEC_MONTH = 12; // month of the year.
  private final int START_OF_YEARS = 1000, END_OF_YEARS = 9999; // range of valid years
  private final int NUM_FOUR = 4, NUM_ONE_HUND = 100, NUM_FOUR_HUND = 400; // parameters for checking if the year is leap.
  private final int TWO_NINE = 29, TWO_EIGHT = 28, THREE_Z = 30, THREE_ONE = 31 , NINE = 9 , TEN = 10; // days of month.
  
  /**
   * Copy constructor
   * @param other - the Date to be copied
   */
  public Date(Date other)
  {
      this._day = other._day;
      this._month = other._month;
      this._year = other._year;
  }

  private boolean checkIfValid(int day, int month, int year) //the method cheking if the day, month and year are valid.
  {
      if(month == FEB_MONTH && year >= START_OF_YEARS && year <= END_OF_YEARS) // checking if the year is leap.
      {
          if( year % NUM_FOUR == 0 && year % NUM_ONE_HUND == 0 && year % NUM_FOUR_HUND == 0 )
          {
                  if( day >= 1 && day <= TWO_NINE ) // the case of leap year.
                     return true;
                  else
                     return false;
          }
          else if( day >= 1 && day <= TWO_EIGHT) // the case of normal year.
              return true;
      }
      else if(month == 1 || month <= DEC_MONTH && month != FEB_MONTH ) // checking the case of other years that not leap.
      {
          if (day >= 1 && day <= THREE_ONE && year >= START_OF_YEARS && year <= END_OF_YEARS)
              return true;
      }
      return false;
  }
  /**
   * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
   * @param day - the day in the month (1-31)
   * @param month - the month in the year (1-12)
   * @param year - year - the year (4 digits)
   */
  public Date(int day, int month, int year)
  {
      if(checkIfValid(day, month, year)) // using the method of cheking the date.
      {
          _day = day;
          _month = month;
          _year = year;
      }
      else // setting the defualt date.
      {
          _day = DEFUALT_DAY;
          _month = DEFUALT_MONTH;
          _year = DEFUALT_YEAR; 
      }
  }
  /**
   * Gets the day
   * @return the day
   */
  public int getDay()
  {
      return _day;
  }
  /**
   * Gets the month
   * @return the month
   */
  public int getMonth()
  {
      return _month;
  }
  /**
   * Gets the year
   * @return the year
   */
  public int getYear()
  {
      return _year;
  }
  /**
   * Set the day (only if date remains valid)
   * @param dayToSet - the day value to be set
   */
  public void setDay(int dayToSet)
  {
      if(checkIfValid(dayToSet, _month, _year)) // checking if the day is valid.
          _day = dayToSet;
      else
          _day = _day;
  }
  /**
   * Set the month (only if date remains valid)
   * @param monthToSet - the month value to be set
   */
  public void setMonth(int monthToSet)
  {
      if(checkIfValid(_day, monthToSet, _year)) // checking if the month is valid.
          _month = monthToSet;
      else
          _month = _month;
  }
  /**
   * Sets the year (only if date remains valid)
   * @param yearToSet - the year value to be set
   */
  public void setYear(int yearToSet)
  {
      if(checkIfValid(_day, _month, yearToSet)) // cheking if the year is valid.
          _year = yearToSet;
      else
          _year = _year;
  }
  /**
   * Check if 2 dates are the same 
   * @param other - date to compare this date to
   * @return - true if the dates are the same, otherwise false
   */
  public boolean equals (Date other) // checking if two dates are equal
  {
      return ((this._year == other._year && this._month == other._month && this._day == other._day));
  }
  /**
   * Check if this date is before other date
   * @param other - date to compare this date to
   * @return - true if this date is before other date, otherwise false
   */
  public boolean before (Date other) // cheking if one date before the other.
  {
      if(other._year > this._year)
          return true;
      else if (other._month > this._month && other._year == this._year)
          return true;
      else if (other._day > this._day && other._month == this._month && other._year == this._year)
          return true;
      return false;
  }
  /**
   * Check if this date is after other date
   * @param other - date to compare this date to
   * @return - true if this date is after other date, otherwise false
   */
  public boolean after (Date other) // checking if one date after the other on the base of before method.
  {
      if(other.before(this))
          return true;
      return false;
  }
  /**
   * Calculates the difference in days between two dates
   * @param other -  the date to calculate the difference between
   * @return - the number of days between the dates (non negative value)
   */
  public int difference (Date other)
  {
      return Math.abs((calculateDate(this._day, this._month, this._year)) - 
      (calculateDate(other._day, other._month, other._year))); // calculating the differnce between to dates and returning the absolute duffrence.
  }
  /**
   * Calculate the date of tomorrow
   * @return the date of tomorrow
   */
  public Date tomorrow() // calculating the day after given date.
  { 
      Date dFlag = new Date (_day, _month, _year); // making new date object to return without changing the original date object.
      if(_day == THREE_ONE && _month == DEC_MONTH) // cheking edge of month.
      {
          dFlag.setDay(1);
          dFlag.setMonth(1);
          dFlag.setYear(_year+1);
      }
      else if (_day == THREE_ONE && _month <= JUL_MONTH && _month % 2 == 1)// cheking edge of month.
      {
          dFlag.setDay(1);
          dFlag.setMonth(_month+1);
          dFlag.setYear(_year);
      }
      else if(_day == THREE_Z && _month >= AUG_MONTH && _month % 2 == 1)// cheking edge of month.
      {
          dFlag.setDay(1);
          dFlag.setMonth(_month+1);
          dFlag.setYear(_year);
      }
      else if(_day == THREE_Z && _month <= JUL_MONTH && _month % 2 == 0)// cheking edge of month.
      {
          dFlag.setDay(1);
          dFlag.setMonth(_month+1);
          dFlag.setYear(_year);
      }
     else if(_day == THREE_ONE && _month >= AUG_MONTH && _month % 2 == 0)// cheking edge of month.
      {
          dFlag.setDay(1);
          dFlag.setMonth(_month+1);
          dFlag.setYear(_year);
      } 
     else if ( _day == TWO_NINE && _month == FEB_MONTH &&  checkIfValid(_day, _month, _year) == true ) // cheking the edge of leap year in feb month.
      {
          dFlag.setDay(1);
          dFlag.setMonth(3);
          dFlag.setYear(_year);
      }
     else if (_day == TWO_EIGHT && _month == FEB_MONTH && checkIfValid(_day, _month, _year) == false ) // cheking the edge of leap year in feb month.
      {
          dFlag.setDay(1);
          dFlag.setMonth(3);
          dFlag.setYear(_year);
      }
      else // the normal day option that need to change only the day parameter.
          dFlag.setDay(_day+1); 
      return dFlag;
  }
  /**
   * Returns a String that represents this date 
   * @return String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits)
   * for example: 02/03/1998
   */
  public String toString()
  {
    if(_day< TEN && _month > NINE)
        return "0"+_day+"/"+ _month + "/" + _year;
    if(_day > NINE && _month < TEN)
        return _day+"/0"+ _month + "/" + _year;
    if(_day < TEN && _month < TEN)
         return "0"+_day+"/0"+ _month + "/" + _year;
    return _day +"/" + _month + "/" + _year;
  }
  private int calculateDate ( int day, int month, int year) // method that claculating the number of days that past from the beginning of counting.
  {
     if (month < 3)
     {
         year--;
     month = month + 12;
     }
     return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
  } 
  
}
