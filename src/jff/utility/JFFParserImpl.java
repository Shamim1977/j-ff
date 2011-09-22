package jff.utility;

public class JFFParserImpl implements JFFParser {

	private final static String Separator=":"; 
	private String Line; 
	
	public JFFParserImpl(String line){
		Line=line;
	}
	
	@Override
	public boolean isEmpty(){
		return Line==null;
	}
	
	@Override
	public boolean find(String s){
		if (!isEmpty())
			return Line.split(Separator)[0].equals(s);
	
		return false;
	}

	@Override
	public String getString(){
	
		return Line.split(Separator)[1].trim();
	}

	@Override
	public boolean getBoolean(){
		
		if (Line.split(Separator)[1].trim().equals("true"))
			return true;
		if (Line.split(Separator)[1].trim().equals("false"))
				return false;
		
		return false;//TODO
	}
	
	@Override
	public int getInt(){
		
		return Integer.parseInt(Line.split(Separator)[1].trim());
		}
	
	
}