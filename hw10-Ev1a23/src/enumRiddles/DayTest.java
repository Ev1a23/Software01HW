package enumRiddles;

import java.util.Arrays;

enum Day {
	   MONDAY,
	   TUESDAY,
	   WEDNESDAY,
	   THURSDAY,
	   FRIDAY,
	   SATURDAY,
	   SUNDAY;
	 
	   public Day next(){
		   if(this.equals(Day.SUNDAY))
		   {
			   return Day.MONDAY;
		   }
		   return Day.values()[getDayNumber()];

	   }
	 
	   int getDayNumber() {
		   if(this.equals(Day.SUNDAY))
		   {
			   return 7;
		   }
		   Day[] lst = Day.values();
		   int i = 1;
		   for(i = 1; i<lst.length; i++)
		   {
			   if(this == lst[i-1])
			   {
				   return i;
			   }
		   }
		   return i;
	   }
//
//		int getDayNumber1() {
//
//			if(this.equals(Day.SUNDAY))
//			{
//				return 7;
//			}
//			Day[] lst = Day.values();
//			int i = 1;
//			for(i = 1; i<lst.length; i++)
//			{
//				if(this == lst[i-1])
//				{
//					return i;
//				}
//			}
//			return i;
//		}


	}
	   
	public class DayTest {
	   public static void main(String[] args) {
	      for (Day day : Day.values()) {
	         System.out.printf("%s (%d), next is %s\n", day, day.getDayNumber(), day.next());
	      }
	   }
	}
