/**
 * 
 */
package pt.carlos.alex.rosario;

/**
 * @author alexandre
 *
 */
public class Pagina {

	private int mPagina;
	
	/**
	 * 
	 */
	public Pagina() {
		setPagina(0);
	}
	
	/**
	 * 
	 */
	public Pagina (int pagina){
		setPagina(pagina);
	}

	/**
	 * @return the mPagina
	 */
	public int getPagina() {
		return mPagina;
	}

	/**
	 * @param mPagina the mPagina to set
	 */
	public void setPagina(int mPagina) {
		this.mPagina = mPagina;
	}
	
	/**
	 * @return String representation of Pagina
	 */
	@Override
	public String toString() {
		return "Pagina [mPagina=" + mPagina + "]";
	}
}
