import Estrutura.Grafo;

public class teste {

	public static void main(String[] args) {
		Grafo<Integer, String> g = new Grafo<Integer, String>();

		g.AddVertice(0, "Paulo");
		g.AddVertice(1, "Lucas");
		g.AddVertice(2, "Vera");
		g.AddVertice(3, "Vitória");
		g.AddVertice(4, "Marcos");

		g.AddAresta(0, 4, 1);
		g.AddAresta(4, 1, 1);
		g.AddAresta(4, 2, 1);
		g.AddAresta(4, 3, 1);
		g.AddAresta(1, 2, 1);
		g.AddAresta(2, 3, 1);

		g.ExibirAdj(1);
		// System.out.println(g.ehConexo(1));

		// System.out.print(g.ehCompleto());

		
		// c.ExibirPorNumAdj();

	}

}
