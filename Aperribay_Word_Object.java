//Author Name: Iker Aperribay
//Date: 2/1/2020
//Program Name: Aperribay_Word_Object
//Purpose: Create an object to attach frequency to word

public class Aperribay_Word_Object
{
	int frequency;
	String word;

	public Aperribay_Word_Object (int frequency, String word) 
	{
		this.frequency = frequency;
		this.word = word;
	}
	
	@Override
	public String toString() {
		return   word +" "+ frequency;
	}
}
