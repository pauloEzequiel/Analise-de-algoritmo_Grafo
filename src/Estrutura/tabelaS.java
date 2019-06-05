package Estrutura;

public class tabelaS {

	private int v;
	private boolean S;
	private double dist;
	private int path;
	
	public tabelaS(int v, boolean s) {
		this.v = v;
		S = false;
		this.dist = 999999;
		this.path = -1;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public boolean isS() {
		return S;
	}

	public void setS(boolean s) {
		S = s;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	public int getPath() {
		return path;
	}

	public void setPath(int path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "tabelaS [v=" + v + ", S=" + S + ", dist=" + dist + ", path=" + path + "]";
	}
	
	
	
	
}
