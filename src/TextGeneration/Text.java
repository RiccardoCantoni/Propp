package TextGeneration;

public class Text {
	
	public String title;
	public String[] body;
	public int length;
	
	public void prettyPrint() {
		System.out.println(title);
		System.out.println("");
		for (String line : body) {
			System.out.println(line);
		}
	}

}
