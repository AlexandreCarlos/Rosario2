/**
 * 
 */
package pt.carlos.alex.rosario;

/**
 * @author alexandre
 *
 */
public class Estado {

	private int mDiaSemana;
	private int mMisterio;
	private int mPagina;
	private Boolean mDualPage;
	/**
	 * 
	 */
	public Estado() {
		this.setDiaSemana(-1);
		this.setMisterio(-1);
		this.setPagina(-1);
		this.setDualPage(false);
	}

	public Estado(int diaSemana, int misterio, int pagina, Boolean dualPage) {
		this.setDiaSemana(diaSemana);
		this.setMisterio(misterio);
		this.setPagina(pagina);
		this.setDualPage(dualPage);
	}

	/**
	 * @return the mDiaSemana
	 */
	public int getDiaSemana() {
		return mDiaSemana;
	}

	/**
	 * @param mDiaSemana the mDiaSemana to set
	 */
	public void setDiaSemana(int mDiaSemana) {
		this.mDiaSemana = mDiaSemana;
	}

	/**
	 * @return the mMisterio
	 */
	public int getMisterio() {
		return mMisterio;
	}

	/**
	 * @param mMisterio the mMisterio to set
	 */
	public void setMisterio(int mMisterio) {
		this.mMisterio = mMisterio;
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
	 * @return the mDualPage
	 */
	public Boolean isDualPage() {
		return mDualPage;
	}

	/**
	 * @param mDualPage the mDualPage to set
	 */
	public void setDualPage(Boolean mDualPage) {
		this.mDualPage = mDualPage;
	}
	
}
