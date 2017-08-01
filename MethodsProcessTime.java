package com.capitalgbl.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*	Plan using the Reflection feature to read class method and invoke at runtime
 *   then print the process time
 */

public class MethodsProcessTime {

	private String methodName;
	private String className;
	private String parameterType;
	private String parameterValue;
	
	public MethodsProcessTime(String methodName, String className, String parameterType,String ParameterValue){
		this.methodName=methodName;
		this.className=className;
		this.parameterType = parameterType;
		this.parameterValue=parameterValue;
	}
	
	
	public Class<?> getGenericParameterType(String parmType){
		if ("Boolean.class".equalsIgnoreCase(parmType)){
			return Boolean.class;
		}else if("Byte.class".equalsIgnoreCase(parmType)){
			return 	Byte.class;
		}else if("Short.class".equalsIgnoreCase(parmType)){
			return 	Short.class;
		}else if("Integer.class".equalsIgnoreCase(parmType)){
			return 	Integer.class;
		}else if("Long.class".equalsIgnoreCase(parmType)){
			return 	Long.class;			
		}else if("Float.class".equalsIgnoreCase(parmType)){
			return 	Float.class;	
		}else if("Double.class".equalsIgnoreCase(parmType)){
			return 	Double.class;
		}else if("String.class".equalsIgnoreCase(parmType)){
				return 	String.class;
		}

		return null;
	}
	
	public Class<?>[] convertParameterType(String parameterType) throws ClassNotFoundException{
		if (parameterType==null) return null;
		
		String[] parmsTypes = parameterType.split(",");
		Class<?>[] cls = new Class[parmsTypes.length];
		
		for(int i=0; i < parmsTypes.length; i++){
			cls[i] = getGenericParameterType(parmsTypes[i]);
		}
		if (cls.length > 0){
			return cls;//
		}

		return null;
		
	}
	
	public Object getGenericTypeValue(Class<?> type, String value){
		
		if (type.isAssignableFrom(Boolean.class)) {
			return ("null".equals(value) ? null : Boolean.parseBoolean(value));
		}else if (type.isAssignableFrom(Byte.class)) {
			return ("null".equals(value) ? null : Byte.parseByte(value));
		}else if (type.isAssignableFrom(Short.class)) {
			return ("null".equals(value) ? null : Short.parseShort(value));
		}else if (type.isAssignableFrom(Integer.class)) {
			return ("null".equals(value) ? null : Integer.parseInt(value));
		}else if (type.isAssignableFrom(Long.class)) {
			return ("null".equals(value) ? null : Long.parseLong(value));
		}else if (type.isAssignableFrom(Float.class)) {
			return ("null".equals(value) ? null : Float.parseFloat(value));
		}else if (type.isAssignableFrom(Double.class)) {
			return ("null".equals(value) ? null : Double.parseDouble(value));
		}else if (type.isAssignableFrom(String.class)) {
			return ("null".equals(value) ? null : value);
		}
		//}else if (type.isAssignableFrom(Boolean.class)) {
		//	return ("null".equals(value) ? null : Boolean.parseBoolean(value));	
		return null;
	}
	
	public Object[] convertParameterValue(String parameterValue,Class<?>[] type) throws ClassNotFoundException{
		if (parameterValue==null) return null;
		
		String[] parmsValues = parameterValue.split(",");
		List<Object> list = new ArrayList<Object>();
		for(int i=0; i < parmsValues.length; i++){
			list.add((Object)getGenericTypeValue(type[i],parmsValues[i]));
		}
		if (list.size() > 0){
			return (Object[])list.toArray();
		}
//		if (parmsTypes.length > 0){
//			return parmsTypes;
//		}
		return null;
		
	}
	
	//ReflectiveOperationException
	public static void main(String[] args) throws ClassNotFoundException,ReflectiveOperationException,Exception  {
		
		MethodsProcessTime methodsOfClass=new MethodsProcessTime("populateStudent","com.capitalgbl.test.StudentGPATest",null,null);
		//methodsOfClass.performMethodTimeCheck("populateStudent","com.capitalgbl.test.StudentGPATest",null,null); 
		
		methodsOfClass.performMethodTimeCheck("populateStudentInfo","com.capitalgbl.test.Student",
				"String.class,Integer.class,FLoat.class","Ali,200,4.44"); 
		
		
//		Class objClass = Class.forName(methodsOfClass.getClassName());
//		Method objMethod = objClass.getMethod(methodsOfClass.getMethodName(),(Class[])null);
//		Object test = objClass.newInstance();
//		System.out.println("date start"+Calendar.getInstance().getTimeInMillis());
//		Object o = objMethod.invoke(test, (Class[])null);
//		System.out.println("date end"+Calendar.getInstance().getTimeInMillis());
	}
	
	public void performMethodTimeCheck(String methodName, String className, String parameterType,String ParameterValue)
			throws ClassNotFoundException,ReflectiveOperationException,Exception {
		
		Class objClass = Class.forName(className);
		Object test = objClass.newInstance();
		Method objMethod ;
		if (parameterType==null){
			
			objMethod = objClass.getMethod(methodName,(Class[])null);
			objMethod.setAccessible(true);
			System.out.println("date start"+Calendar.getInstance().getTimeInMillis());
			Object o = objMethod.invoke(test, (Class[])null);
			System.out.println("date end"+Calendar.getInstance().getTimeInMillis());
				
		}else{
			//set value to method
			Class[] type =(Class[])convertParameterType(parameterType);
			objMethod = objClass.getDeclaredMethod(methodName,(Class[])type );
			objMethod.setAccessible(true);
			System.out.println("date start"+Calendar.getInstance().getTimeInMillis());
			Object o = objMethod.invoke(test, (Object[])convertParameterValue(ParameterValue,type));
			System.out.println("date end"+Calendar.getInstance().getTimeInMillis());
		}
		
	}

	public String getMethodName() {
		return methodName;
	}

	public String getClassName() {
		return className;
	}
}
