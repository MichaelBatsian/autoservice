package services.utils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UtilsService {

	public static <T> List<T> sortUniqueElements(ArrayList<T> aray){
		List<T> list = new ArrayList<>(new HashSet<T>(aray));
		
		Collections.sort(list, new Comparator<T>(){
			public int compare(T o1,T o2){
				return o1.toString().compareTo(o2.toString());
			}
		});
		return list;
	}
	
	public static Time addIntHoursToTime(Time time, int manHours){
		String [] timeStr = time.toString().split(":");
		int hours = Integer.parseInt(timeStr[0]);
		String minutes = timeStr[1];
		String seconds = timeStr[2];
		int newHours=hours+manHours;
		StringBuilder sumHours = new StringBuilder();
		sumHours.append(newHours);
		sumHours.append(":");
		sumHours.append(minutes);
		sumHours.append(":");
		sumHours.append(seconds);
		time = Time.valueOf(sumHours.toString());
		return time;
	}

}
