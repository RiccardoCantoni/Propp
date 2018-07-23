package TextGeneration;

public class SpecialElement implements TextElement{
	
	String str;
	
	public SpecialElement(String str) {
		this.str = str;
	}

	@Override
	public String yield() {
		return "--"+str+"--";
	}

}
