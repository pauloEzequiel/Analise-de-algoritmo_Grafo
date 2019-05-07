package Estrutura;


public class Vertice<K extends Comparable<? super K>, V> {

	K Label;
	V val;
	int index;
    boolean foiVisitado;
    int numAdjacente;
    int colorido;
	public Vertice(K label, V val,int index) {
		Label = label;
		this.val = val;
		this.index=index;
		this.foiVisitado=false;
		this.numAdjacente=0;
	}
	
	public void IncNumAdj() {numAdjacente++;}
		
	public void DecNumAdj() {numAdjacente--;}
	
	public int getNumAdj() {return numAdjacente;}
}
