package TextGeneration;

public class StringElement implements TextElement{
	
	public StringElement(String str) {
		this.label = str;
	}
	
	String label;
	

	@Override
	public String yield() {
		TextDictionary td = new TextDictionary();
		td.loadDictionary();
		return td.getRandomText(label);
	}

}
