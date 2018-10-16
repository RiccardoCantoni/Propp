package TextGeneration;

public class Text {
	
	public String title;
	public OutputItem[] body;
	
	public Text(String title, OutputItem[] body) {
		this.title = title;
		this.body = body;
	}
	
	public void prettyPrint() {
		System.out.println(title);
		System.out.println("");
		for (OutputItem o : body) {
			System.out.println(o.line);
		}
	}

}
