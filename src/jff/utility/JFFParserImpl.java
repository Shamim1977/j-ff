package jff.utility;

/**
 * An implementation of {@link JFFParser}
 * 
 * @version %I%
 * 
 * @author Francesco Fornasini
 *
 */
public class JFFParserImpl implements JFFParser {

	/**
	 * The String to be parsed
	 */
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
	public String getStringWithSeparators(){
	
		int lenghtOfTheFistPart=Line.split(Separator)[0].length();
		
		return Line.substring(lenghtOfTheFistPart+1).trim();
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
