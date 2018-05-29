package genericGraph;

public class DirectedEdge<T> implements Cloneable{

	public T from, to;
	
	public DirectedEdge(T from, T to) {
		this.from = from;
		this.to = to;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		if (!(o instanceof DirectedEdge)) return false;
		DirectedEdge<T> e = (DirectedEdge<T>)o;
		return this.from.equals(e.from) && this.to.equals(e.to);
	}
	
	@Override
	public String toString() {
		return "e("+from.toString()+","+to.toString()+")";
	}
	
}
