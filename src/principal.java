import java.util.Scanner;

import org.jgap.Chromosome;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class principal {
	public static void main(String[] args) throws Exception {

		System.out.println("Tabla de 4 Reinas");
		int n = 4;
		FAptitud.n = n;

		DefaultConfiguration VConfig = new DefaultConfiguration();
		VConfig.setPreservFittestIndividual(true);

		FAptitud vfuncionapto = new FAptitud();
		VConfig.setFitnessFunction(vfuncionapto);

		// Estructura genetica
		// Estructura de genes

		Gene[] vgene = new Gene[n * n];
		for (int i = 0; i < vgene.length; i++) {
			vgene[i] = new IntegerGene(VConfig, 0, 1);
		}

		// Estructura del cromosoma
		IChromosome vcromosoma = new Chromosome(VConfig, vgene);
		VConfig.setSampleChromosome(vcromosoma);

		// Definir el tamaño de la población
		VConfig.setPopulationSize(5000);

		// Llenado de forma aleatoria con 5000 individuos
		Genotype vpoblacion = Genotype.randomInitialGenotype(VConfig);

		// 50 generaciones realizandose selección, cruce y mutación
		for (int generacion = 1; generacion <= 50; generacion++) {
			vpoblacion.evolve();
		}

		// Extraer el individuo más apto despues de las 50 generaciones
		IChromosome indApto = vpoblacion.getFittestChromosome();

		// Visualizar en pantalla el individuo más apto
		int pos = 0;
		int[][] matriz;
		matriz = new int[n][n];
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (pos < vgene.length) {
					matriz[i][j] = (Integer) indApto.getGene(pos).getAllele();
					pos++;
				}
			}
		}

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println("");
		}

	}

}
