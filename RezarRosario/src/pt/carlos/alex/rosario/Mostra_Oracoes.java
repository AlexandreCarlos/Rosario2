/**
 * Fragment que mostra as orações associadas a um mistério
 */
package pt.carlos.alex.rosario;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * @author alexandre
 * 
 */
@EFragment(R.layout.oracoes_page_layout)
public class Mostra_Oracoes extends SherlockFragment {

	private static final String TAG = "Mostra Orações";
	private static final boolean DEBUG = true;

	private EventBus eventBus;
	private boolean registado = false;

	@ViewById(R.id.pager)
	protected ViewPager pager;

	@ViewById(R.id.indicator)
	ContasRosario mIndicator;

	protected int index_dia_semana = -1;
	protected int misterio_selected = 0;
	public int pagina_actual = 0;
	
	protected List<String> oracao;
	protected List<Integer> coresContas;

	@AfterInject
	void startUp() {
		eventBus = EventBus.getDefault();
		registaBus();

		MainActivity ma = (MainActivity) getActivity();
		index_dia_semana = ma.index_dia_semana;
		misterio_selected = ma.misterio_selected;
		pagina_actual = ma.pagina_actual;
		ma = null;

		if (DEBUG) {
			Log.d(TAG, "inicializa-index_dia_semana:" + index_dia_semana
					+ "; misterio_selected:" + misterio_selected);
		}
	}

	@AfterViews
	void init() {

		try {
			oracao = Misterios.Oracoes_do_Misterio(index_dia_semana,
					misterio_selected);

			this.gerarCoresContas();

			pager.setAdapter(new OracoesPageAdapter(this, oracao));

			final float density = getResources().getDisplayMetrics().density;

			mIndicator.setViewPager(pager);
			mIndicator.setCoresContas(coresContas);

			this.detectaPaginaCorrente();

			mIndicator.setBackgroundColor(getResources().getColor(
					R.color.ics_dark_grey)); // cor de fundo do ViewPage
												// Indicator
			mIndicator.setRadius(6 * density);
			mIndicator.setPageColor(getResources().getColor(R.color.ics_blue)); // Cor
																				// de
																				// fundo
																				// dos
																				// circulos
			mIndicator.setFillColor(getResources().getColor(
					R.color.ics_clear_grey)); // Cor de fundo do circulo da
												// página visivel
			mIndicator.setStrokeColor(getResources()
					.getColor(R.color.ics_black)); // Cor da circunferencia dos
													// circulos

		} catch (Exception e) {
			Log.e(TAG, "Erro no init() @AfterViews:", e);
		}

	}

	private void registaBus() {
		if (!registado) {
			eventBus.register(this);
			registado = true;
		}
	}

	private void desregistaBus() {
		if (registado) {
			eventBus.unregister(this);
			registado = false;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		registaBus();

	}

	@Override
	public void onPause() {
		desregistaBus();
		super.onPause();
	}

	void gerarCoresContas() {

		coresContas = new ArrayList<Integer>();

		coresContas.add(getResources().getColor(R.color.ics_yellow)); // Evangelho
		coresContas.add(getResources().getColor(R.color.ics_green)); // Pai-Nosso

		for (int i = 2; i < 12; i++) {
			coresContas.add(getResources().getColor(R.color.ics_blue)); // Avé-Maria
		}

		coresContas.add(getResources().getColor(R.color.ics_bold_yellow)); // Glória
		coresContas.add(getResources().getColor(R.color.ics_violet)); // Jaculatória

	}

	private void detectaPaginaCorrente() {

		// We set this on the indicator, NOT the pager
		mIndicator
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						pagina_actual = position;
						// Toast.makeText(getSherlockActivity(),
						// "Changed to page " + position,
						// Toast.LENGTH_SHORT).show();
						
						eventBus.post((Integer)position);
						
						if (DEBUG) {
							Log.d(TAG, "onPageSelected-position:"+position);
						}
					}

					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {
					}

					@Override
					public void onPageScrollStateChanged(int state) {
					}
				});
	}

	public void onEvent(Rezar event) {

		if (DEBUG) {
			Log.d(TAG, "Evento recebido:" + event);
		}

		index_dia_semana = event.dia_semana;
		misterio_selected = event.misterio;
		oracao.clear();
		oracao.addAll(Misterios.Oracoes_do_Misterio(index_dia_semana, misterio_selected));
		pager.getAdapter().notifyDataSetChanged();

		this.pager.setCurrentItem(event.pagina);
	}

}
