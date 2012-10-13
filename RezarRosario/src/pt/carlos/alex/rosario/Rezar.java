/**
 * 
 */
package pt.carlos.alex.rosario;

/**
 * @author alexandre
 *
 */
public class Rezar {
	protected int dia_semana;
	protected int misterio;
	
	public Rezar () {
		this.dia_semana = 0;
		this.misterio = 0;
	}

	public Rezar (int dia_semana, int mistério) {
		this.dia_semana = dia_semana;
		this.misterio = mistério;
	}

	/**
	 * @return the dia_semana
	 */
	public int getDia_semana() {
		return dia_semana;
	}

	/**
	 * @param dia_semana the dia_semana to set
	 */
	public void setDia_semana(int dia_semana) {
		this.dia_semana = dia_semana;
	}

	/**
	 * @return the misterio
	 */
	public int getMisterio() {
		return misterio;
	}

	/**
	 * @param misterio the misterio to set
	 */
	public void setMisterio(int misterio) {
		this.misterio = misterio;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rezar [dia_semana=" + dia_semana + ", misterio=" + misterio
				+ "]";
	}
	
	
}
