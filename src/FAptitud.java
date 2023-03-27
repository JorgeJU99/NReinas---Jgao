import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class FAptitud extends FitnessFunction {

	double apto = 0;
	public static int n = 0;

	public double evaluate(IChromosome cromosoma) {
		apto = 0;

		if (evaluarCantidad(cromosoma, n)) {
			evaluarHorizontal(cromosoma, n);
			evaluarVertical(cromosoma, n);
			evaluarDiagonal(cromosoma, n);
		}

		return apto;
	}

	public void evaluarHorizontal(IChromosome cromosoma, int n) {
		int suma = 0;
		int fila = 0;
		int i = 0;

		while (i < n) {
			fila = n * i + (n - 1);
			for (int j = n * (i); j <= fila; j++) {
				suma = suma + (Integer) cromosoma.getGene(j).getAllele();
			}

			if (suma == 1) {
				apto += 1;
				suma = 0;
			}
			i++;
		}

	}

	public void evaluarVertical(IChromosome cromosoma, int n) {
		int suma = 0;
		int i = 0;

		while (i < n) {
			for (int j = i; j < (n * n); j = j + n) {
				suma = suma + (Integer) cromosoma.getGene(j).getAllele();
			}

			if (suma == 1) {
				apto += 1;
				suma = 0;
			}
			i++;
		}
	}

	public void evaluarDiagonal(IChromosome cromosoma, int n) {
		Integer suma = 0;
		int cantidadGen = n * n;

		// Superior derecho
		for (int i = 0; i < n - 1; i++) {
			for (int j = i; j < n * (n - i); j += (n + 1)) {
				Integer vgen = (Integer) cromosoma.getGene(j).getAllele();
				suma += vgen;
			}
			if (suma == 1) {
				apto += 1;
			}
			suma = 0;
		}

		// Inferior derecho
		int v = n + (n - 1);
		for (int i = v; i < cantidadGen - 1; i += n) {
			for (int j = i; j < cantidadGen; j += (n - 1)) {
				Integer vgen = (Integer) cromosoma.getGene(j).getAllele();
				suma += vgen;
			}
			if (suma == 1) {
				apto += 1;
			}
			suma = 0;
		}

		// Superior izquierda
		for (int i = 1; i < n; i++) {
			for (int j = i; j <= n * i; j += (n - 1)) {
				Integer vgen = (Integer) cromosoma.getGene(j).getAllele();
				suma += vgen;
			}
			if (suma == 1) {
				apto += 1;
			}
			suma = 0;
		}

		// Inferior izquierda
		for (int i = n; i < n * (n - 1); i += n) {
			for (int j = i; j < cantidadGen; j += (n + 1)) {
				Integer vgen = (Integer) cromosoma.getGene(j).getAllele();
				suma += vgen;
			}
			if (suma == 1) {
				apto += 1;
			}
			suma = 0;
		}
	}

	public boolean evaluarCantidad(IChromosome cromosoma, int n) {
		int nReinas = 0;
		int cantidadGen = n * n;
		for (int i = 0; i < cantidadGen; i++) {
			int vgen = (Integer) cromosoma.getGene(i).getAllele();
			nReinas += vgen;
		}
		if (nReinas == n) {
			return true;
		} else {
			return false;
		}
	}
}
