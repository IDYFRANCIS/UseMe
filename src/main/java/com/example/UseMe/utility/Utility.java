package com.example.UseMe.utility;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

//import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.apache.commons.validator.routines.EmailValidator;

import com.google.common.hash.Hashing;

public class Utility {
	
	public static int generateDigit (){
		Random random = new Random();
		int numb = random.nextInt(90000);
		return numb;
	}
	
	public static String generateString(int numb){
		
		String newNumb = (numb + 1) + "";
		String returnValue = "";
		
		if(newNumb.length() == 1)
			returnValue = "0000" + newNumb;
		
		if(newNumb.length() == 2)
			returnValue = "000" + newNumb;
		
		if(newNumb.length() == 3)
			returnValue = "00" + newNumb;
		
		if(newNumb.length() == 4)
			returnValue = "0" + newNumb;
		
		return returnValue;
		
	}
	
	
	 public static boolean isNull(Object object) {
	        return (object == null);
	    }

	    public static ArrayList<Object> validateFields(Object[] fields, Object[] names) {
	        ArrayList<Object> errors = new ArrayList<Object>();
	        int i = 0;
	        for (Object field : fields) {
	            if (field == null || field.toString()
	                .isEmpty()
	                || field.toString()
	                    .equalsIgnoreCase(" ")) {
	                errors.add(names[i]);
	            }
	            i++;
	        }
	        return errors;
	    }
	    
	    public static boolean isValidEmail(String email){
	        return EmailValidator.getInstance(true).isValid(email);
	    }
	    
	    public static boolean isValidPhone(String phone){
	        return phone.startsWith("+234") && phone.length() > 13 || phone.startsWith("070") && phone.length() > 9
	        		|| phone.startsWith("080") && phone.length() > 9 || phone.startsWith("090") && phone.length() > 9
	        		|| phone.startsWith("0") && phone.length() > 9;
	    }

	    @SuppressWarnings("unchecked")
	    public static <T> T[] arrayMerge(T[] array1, T[] array2, T[] array3) {
	        ArrayList<T> list = new ArrayList<T>();
	        list.addAll(Arrays.asList(array1));
	        list.addAll(Arrays.asList(array2));
	        list.addAll(Arrays.asList(array3));
	        T[] result = (T[]) Array.newInstance(array1.getClass()
	            .getComponentType(), list.size());
	        result = (T[]) list.toArray(result);
	        return result;
	    }

	    public static String arrayToString(ArrayList<Object> list) {
	        return list.toString()
	            .substring(1, list.toString()
	                .length() - 1);
	    }

	    public static Object[] objectArrayFromArrayList(ArrayList<Object> list) {
	        Object[] array = new Object[list.size()];
	        return list.toArray(array);
	    }

	    public static String capitalizeFirstLetter(String text) {
	        return text.substring(0, 1)
	            .toUpperCase()
	            .concat(text.substring(1, text.length()));
	    }

	    public static String today() {
	        return Calendar.getInstance()
	            .get(Calendar.YEAR) + "-"
	            + Calendar.getInstance()
	                .get(Calendar.MONTH)
	            + "-" + Calendar.getInstance()
	                .get(Calendar.DAY_OF_MONTH);
	    }

	    public static String hashPassword(Object password) {
	        return Hashing.sha512()
	            .hashString(password.toString(), Charset.defaultCharset())
	            .toString();
	    }

	    public static String toBase64(Object password) {
	        try {
	            return "Basic " + new String(Base64.getEncoder()
	                .encode(password.toString()
	                    .getBytes("utf-8")));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static boolean comparePasswords(String newPassword, String oldHash) {
	        return hashPassword(newPassword).equals(oldHash);
	    }

	    public static Date toDate(String dateString) {
	        Date date = new Date();
	        try {
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
	            date = format.parse(dateString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return date;
	    }

	    public static String toDate() {
	        String date = null;
	        try {
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	            date = format.format(new Date());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return date;
	    }

	    public int getYearFromDate(String date) {
	        SimpleDateFormat format = new SimpleDateFormat("yyyy");
	        @SuppressWarnings("deprecation")
	        Date instantDate = new Date(date);
	        return Integer.parseInt(format.format(instantDate));
	    }

	    public static Date getDateFromLocalDate(LocalDate localDate) {
	        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault())
	            .toInstant());
	        return date;
	    }

	    public static Date toShortDate(String dateString) {
	        Date date = new Date();
	        try {
	            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	            date = format.parse(dateString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return date;
	    }

	    public Date removeTime(Date date) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }

}
