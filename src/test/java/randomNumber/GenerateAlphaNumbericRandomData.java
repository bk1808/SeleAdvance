package randomNumber;

public class GenerateAlphaNumbericRandomData {

	public static void main(String[] args) {
// 	This program is mainly useful for banking applications where we need to generate alphanumberic data
// 	step-1: declare and initialize a length of the alphanumeric data
	int n=20;
		
//	step-2: create a string of uppercase, lowercase, numbers and special character string and choose a character from this string
	String AlphanumericString="ABCDEFGHIJKLMNOPQRSTUVWXYZ@#$%&*!0123456789abcdefghijklmnopqrstuvwxyz";
	
//	step-3: create StringBuilder size of AlphanumericString
	StringBuilder sb=new StringBuilder(n);
	
//	step-4: generate a random between 0 to AlphanumericString variable length
	
	for(int i=0 ; i<n ; i++) {
		int index = (int)(AlphanumericString.length()*Math.random());
		
		//step-5: add characters one-by-one in the end of StringBuilder sb by using CharAt(i) int index
		sb.append(AlphanumericString.charAt(index));
	}
	
	System.out.println(sb);
		
	}

}
