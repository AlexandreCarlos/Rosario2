/**
 * Fragment que mostra as orações associadas a um mistério
 */
package pt.carlos.alex.rosario;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

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

	private static final String TAG = "Rosário.Mostra_Oracoes";
	// private static final boolean DEBUG = true;

	private EventBus mEventBus;
	private boolean mRegistado = false;

	@ViewById(R.id.pager)
	protected ViewPager mPager;

	@ViewById(R.id.indicator)
	ContasRosario mIndicator;

	@ViewById(R.id.textMisterio)
	TextView mTextMisterio;

	protected int mIndexDiaSemana = -1;
	protected int mMisterioSelected = 0;
	public int mPaginaActual = 0;

	protected List<String> mOracao;
	protected List<Integer> mCoresContas;

	@AfterInject
	void startUp() {
		mEventBus = EventBus.getDefault();
		registaBus();

		FragmentActivity fragmentActivity = getActivity();

		// Log.i(TAG, "Activity mother:"+fragmentActivity.getClass());

		if (fragmentActivity.getClass() == MainActivity_.class) {

			// Log.i(TAG, "Argumentos da MainActivity");

			MainActivity mMa = (MainActivity) getActivity();
			mIndexDiaSemana = mMa.mIndexDiaSemana;
			mMisterioSelected = mMa.mMisterioSelected;
			mPaginaActual = mMa.mPaginaActual;
			mMa = null;
		} else {
			if (fragmentActivity.getClass() == ActivityMostraOracoes_.class) {

				// Log.i(TAG, "Argumentos da ActivityMostraOracoes");

				ActivityMostraOracoes mMa = (ActivityMostraOracoes) getActivity();
				mIndexDiaSemana = mMa.index_dia_semana;
				mMisterioSelected = mMa.misterio_selected;
				mPaginaActual = mMa.pagina_actual;
				mMa = null;
			}
		}

		// MainActivity ma = (MainActivity) getActivity();
		// mIndexDiaSemana = ma.index_dia_semana;
		// mMisterioSelected = ma.misterio_selected;
		// mPaginaActual = ma.pagina_actual;
		// ma = null;

		if (V.DEBUG) {
			Log.d(TAG, "Inicializa-mIndexDiaSemana:" + mIndexDiaSemana
					+ "; mMisterioSelected:" + mMisterioSelected
					+ "; mPaginaActual:" + mPaginaActual);
		}

	}

	@AfterViews
	void init() {

		try {
			mOracao = Misterios.oracoesDoMisterio(mIndexDiaSemana,
					mMisterioSelected);

			identificarMisterio();

			this.gerarCoresContas();

			mPager.setAdapter(new OracoesPageAdapter(this, mOracao));

			final float density = getResources().getDisplayMetrics().density;

			mIndicator.setViewPager(mPager);
			mIndicator.setCoresContas(mCoresContas);

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
		if (!mRegistado) {
			mEventBus.register(this);
			mRegistado = true;
		}
	}

	private void desregistaBus() {
		if (mRegistado) {
			mEventBus.unregister(this);
			mRegistado = false;
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

	private void gerarCoresContas() {

		mCoresContas = new ArrayList<Integer>();

		mCoresContas.add(getResources().getColor(R.color.ics_yellow)); // Evangelho
		mCoresContas.add(getResources().getColor(R.color.ics_green)); // Pai-Nosso

		for (int i = 2; i < 12; i++) {
			mCoresContas.add(getResources().getColor(R.color.ics_blue)); // Avé-Maria
		}

		mCoresContas.add(getResources().getColor(R.color.ics_bold_yellow)); // Glória
		mCoresContas.add(getResources().getColor(R.color.ics_violet)); // Jaculatória

	}

	private void identificarMisterio() {

		mTextMisterio.setText(Misterios.identificarMisterioDia(
				mIndexDiaSemana, mMisterioSelected));

	}

	private void detectaPaginaCorrente() {

		// We set this on the indicator, NOT the mPager
		mIndicator
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						mPaginaActual = position;
						// Toast.makeText(getSherlockActivity(),
						// "Changed to page " + position,
						// Toast.LENGTH_SHORT).show();

						mEventBus.post(new Pagina(position));

						if (V.DEBUG) {
							Log.d(TAG, "onPageSelected-position:" + position);
							Log.d(TAG, "Página (Integer) mEventBus generated");
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

		if (V.DEBUG) {
			Log.d(TAG, "Evento Rezar recebido:" + event);
		}

		mIndexDiaSemana = event.dia_semana;
		mMisterioSelected = event.misterio;

		identificarMisterio();

		mOracao.clear();
		mOracao.addAll(Misterios.oracoesDoMisterio(mIndexDiaSemana,
				mMisterioSelected));
		mPager.getAdapter().notifyDataSetChanged();

		this.mPager.setCurrentItem(event.pagina);
	}

}
