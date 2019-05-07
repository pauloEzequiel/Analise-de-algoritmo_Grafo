package Estrutura;

import java.util.LinkedList;
import java.util.Stack;

public class Grafo<K extends Comparable<? super K>, V> {

	private double MatAdj[][];
    private LinkedList<Vertice<K, V>> vertice;
	private int nVertice;
	int Qtd_Max_Vertice = 1500;
	private Stack<Integer> Pilha;

	public Grafo() {
		nVertice = 0;
		vertice = new LinkedList<Vertice<K, V>>();
		MatAdj = new double[Qtd_Max_Vertice][Qtd_Max_Vertice];
		Pilha = new Stack();
		for (int i = 0; i < Qtd_Max_Vertice; i++) {
			for (int j = 0; j < Qtd_Max_Vertice; j++)
				MatAdj[i][j] = 0;
		}
	}
	
	public void atualizarV(int index,Object v ){
			
		vertice.set(index, (Vertice<K, V>) v);
	}

	public void AddVertice(K label, V valor) {
		if (nVertice <= Qtd_Max_Vertice) {
			int cont = 0;
			for (Vertice<K, V> v : vertice) {
				if (label.compareTo(v.Label) == 0) {
					cont++;
					break;
				}
			}

			if (cont == 0) {
				vertice.addLast(new Vertice<K, V>(label, valor, nVertice++));
				//System.out.println("Realizado com sucesso");
			} else
				System.out.println("Falha, Chave duplicada");
		}

	}

	
	public void AddAresta(int Vinicio, int Vfim, double peso) {
		if (Vinicio > -1 && Vfim < nVertice) {
			MatAdj[Vinicio][Vfim] = peso;
			MatAdj[Vfim][Vinicio] = peso;
			if (Vinicio != Vfim) {
				vertice.get(Vinicio).IncNumAdj();
				vertice.get(Vfim).IncNumAdj();
			} else {
				vertice.get(Vinicio).IncNumAdj();
			}
		} else
			System.out.println("Falha, Valores Informados Inválidos");
	}

	public void ExibirVertice(int v) {
		System.out.print(vertice.get(v).Label + " ");
	}

	public void ExibirAdj(int V) {
		if (V > -1 && V < nVertice) {
			System.out.print("Adjacentes de " + " ");
			ExibirVertice(V);
			System.out.println();
			for (int i = 0; i < nVertice; i++) {
				if (MatAdj[V][i] > 0)
					ExibirVertice(i);
			}
		} else {
			System.out.println("Falha, Valores Informados Inválidos");
		}
	}

	public boolean ehCompleto() {

		for (int i = 0; i < nVertice; i++) {
			for (int j = 0; j < nVertice; j++) {
				if (MatAdj[i][j] < 1)
					return false;
			}
		}

		return true;
	}

	public boolean ehRegular() {
		int cont = 0;
		int qtdVertice = vertice.get(0).numAdjacente;
		for (int i = 1; i < nVertice; i++) {
			if (qtdVertice != vertice.get(i).numAdjacente) {
				cont++;
				break;
			}

		}
		if (cont > 0)
			return false;
		else
			return true;
	}

	public int getAdjNaoVisitado(int v) {
		for (int i = 0; i < nVertice; i++) {
			if (MatAdj[v][i] > 0 && vertice.get(i).foiVisitado == false)
				return i;
		}

		return -1;
	}

	public boolean ehConexo(int v) {
		int cont = 1;
		vertice.get(v).foiVisitado = true;
		 ExibirVertice(v);
		Pilha.push(vertice.get(v).index);
		while (!Pilha.isEmpty()) {
			int vp = getAdjNaoVisitado(Pilha.peek());
			if (vp == -1)
				Pilha.pop();
			else {
				vertice.get(vp).foiVisitado = true;
				cont++;
				 ExibirVertice(vp);
				Pilha.push(vp);
			}
		}
		for (int i = 0; i < nVertice; i++) 
			vertice.get(i).foiVisitado = false;

		return cont == nVertice;
	}

	public double[][] getMatAdj() {
		return MatAdj;
	}

	public LinkedList<Vertice<K, V>> getVertice() {
		return vertice;
	}

	public int getnVertice() {
		return nVertice;
	}	
}
