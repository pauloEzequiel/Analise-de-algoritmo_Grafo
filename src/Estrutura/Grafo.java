package Estrutura;

import java.util.LinkedList;
import java.util.Stack;

public class Grafo<K extends Comparable<? super K>, V> {

	private double MatAdj[][];
    private LinkedList<Vertice<K, V>> vertice;
	private int nVertice;
	int Qtd_Max_Vertice = 1500;
	private Stack<Integer> Pilha;
	private int nTree;
	private int NohAtual;
	private double DistAoNohAtual;
	private DisEPai CaminhosMaiscurto[];

	public Grafo() {
		nVertice = 0;
		vertice = new LinkedList<Vertice<K, V>>();
		MatAdj = new double[Qtd_Max_Vertice][Qtd_Max_Vertice];
		Pilha = new Stack();
		for (int i = 0; i < Qtd_Max_Vertice; i++) {
			for (int j = 0; j < Qtd_Max_Vertice; j++)
				MatAdj[i][j] = 0;
		}
		CaminhosMaiscurto= new DisEPai[Qtd_Max_Vertice];
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
	
	public void Caminho(int ini) {
		int starTree=ini;
		vertice.get(starTree).istree=true;
		nTree=1;
		
		for(int j=0;j<nVertice;j++) {
			double tempDist= MatAdj[starTree][j];
			CaminhosMaiscurto[j]= new DisEPai(starTree, (int)tempDist);
		}
		
		while(nTree < nVertice) {
			int indexMin=getMin();
			double MinDist=CaminhosMaiscurto[indexMin].distancia;
			
			if(MinDist == 9999999) {
				System.out.println("CAMINHO COMPLETO");
				break;
			}
			else{
				NohAtual=indexMin;
				DistAoNohAtual=CaminhosMaiscurto[indexMin].distancia;
			}
			
			vertice.get(NohAtual).istree=true;
			nTree++;
			adjus_Path();
		}
		ExibaCaminho();
		nTree=0;
		for (int i = 0; i < nVertice; i++) {
			vertice.get(i).istree=false;
			
		}
		
	}
	
	public int getMin() {
		double minDist=9999999;
		int indexMin=0;
		
		for(int i=1;i<nVertice;i++) {
			
			if(!vertice.get(i).istree) {
				minDist= CaminhosMaiscurto[i].distancia;
				indexMin=i;
			}
			
		}
		return indexMin;
	}

	public void adjus_Path() {
		int coluna =1;
		while(coluna < nVertice )
		{
			if(vertice.get(coluna).istree) 
			{
				coluna++;
				continue;
			}
			double disAtual = MatAdj[NohAtual][coluna];
			double disInicio= DistAoNohAtual+disAtual;
			double sCaminhoDis= CaminhosMaiscurto[coluna].distancia;
			
			if(disInicio<sCaminhoDis) {
				CaminhosMaiscurto[coluna].NohPai=NohAtual;
				CaminhosMaiscurto[coluna].distancia=disInicio;
			}
			coluna++;
		}
	}
	
	public void ExibaCaminho(){
		for (int i = 0; i <nVertice; i++) {
			System.out.println(vertice.get(i).val +" ");
			if(CaminhosMaiscurto[i].distancia==99999) {
				System.out.print("Inicio");
			}
			else {
				System.out.print(CaminhosMaiscurto[i].distancia);
			
				System.out.println("("+ vertice.get(CaminhosMaiscurto[i].NohPai).val+")");
			}
			System.out.println("");
		}
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
