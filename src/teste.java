import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import Estrutura.Grafo;

public class teste {

	public static void main(String[] args) {
		Grafo<Integer, String> g = new Grafo<Integer, String>();

		g.AddVertice(0, "Aracaju");
		g.AddVertice(1, "Boquim");
		g.AddVertice(2, "Estancia");
		g.AddVertice(3, "Socorro");
		g.AddVertice(4, "Lagarto");

		g.AddAresta(0, 4, 2);
		g.AddAresta(4, 1, 3);
		g.AddAresta(4, 2, 5);
		g.AddAresta(4, 3, 1);
		g.AddAresta(1, 2, 2);
		g.AddAresta(2, 3, 4);

		//g.ExibirAdj(1);
		
		g.Caminho(1);

		/*Grafo<Integer, Integer> grafo = new Grafo<Integer, Integer>();

		FileInputStream stream;
		try {
			stream = new FileInputStream("alue2087.stp");
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();
			int cont = 0;
			while (linha != null && cont < 2) {
				if (linha.equalsIgnoreCase("End")) {
					cont++;
				}
				String[] arg = linha.trim().split(" ");
				if (arg[0].equals("Nodes")) {
					for (int i = 1; i <= Integer.parseInt(arg[1]); i++)
						grafo.AddVertice(i, i);
				}
				else if (arg[0].equals("E")) {
                     grafo.AddAresta(Integer.parseInt(arg[1])
                    		 , Integer.parseInt(arg[2])
                    		 , Integer.parseInt(arg[3]));
				}
				linha = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println(g.ehConexo(1));

		// System.out.print(g.ehCompleto());

		// c.ExibirPorNumAdj();
		grafo.ExibirAdj(3);*/

	}

}
