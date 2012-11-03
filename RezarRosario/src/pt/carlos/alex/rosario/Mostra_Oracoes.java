/**
 * Fragment que mostra as orações associadas a um mistério
 */
package pt.carlos.alex.rosario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;

/**
 * @author alexandre
 * 
 */
@EFragment(R.layout.oracoes_page_layout)
public class Mostra_Oracoes extends SherlockFragment {

	private static final String TAG = "Rosário.Mostra_Oracoes";

	private EventBus mEventBus;
	private boolean mRegistado = false;
	private final CountDownLatch startSignal = new CountDownLatch(2);

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
	void beforeCreate() {
		mEventBus = EventBus.getDefault();

		registaBus();

		if (V.DEBUG) {
			Log.d(TAG, "Inicializa-mIndexDiaSemana:" + mIndexDiaSemana
					+ "; mMisterioSelected:" + mMisterioSelected
					+ "; mPaginaActual:" + mPaginaActual);
			Log.d(TAG, "beforeCreate.startSignal=" + startSignal.getCount());
		}
	}

	@AfterViews
	void afterCreate() {

		try {

			startSignal.countDown();

			if (V.DEBUG) {
				Log.d(TAG, "afterCreate.startSignal=" + startSignal.getCount());
			}
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

	@Background
	protected void initDezena() {
		try {
			mCoresContas = new ArrayList<Integer>();

			mCoresContas.add(getResources().getColor(R.color.ics_yellow)); // Evangelho
			mCoresContas.add(getResources().getColor(R.color.ics_green)); // Pai-Nosso

			for (int i = 2; i < 12; i++) {
				mCoresContas.add(getResources().getColor(R.color.ics_blue)); // Avé-Maria
			}

			mCoresContas.add(getResources().getColor(R.color.ics_bold_yellow)); // Glória
			mCoresContas.add(getResources().getColor(R.color.ics_violet)); // Jaculatória

			if (V.DEBUG) {
				Log.d(TAG, "initDezena before.await.startSignal=" + startSignal.getCount());
			}
			
			startSignal.await();  // Espera pela conclusão da inicialização do estado e das Views
			
			if (V.DEBUG) {
				Log.d(TAG, "initDezena after.await.startSignal=" + startSignal.getCount());
			}
			
			mOracao = Misterios.oracoesDoMisterio(mIndexDiaSemana,
					mMisterioSelected);

			geraPageView();

		} catch (InterruptedException e) {
			Log.e(TAG, "initDezena startSignal await Interrupted", e);
		}
	}

	private void identificarMisterio() {

		mTextMisterio.setText(Misterios.identificarMisterioDia(mIndexDiaSemana,
				mMisterioSelected));

	}

	/**
	 * 
	 */
	@UiThread
	protected void geraPageView() {

		identificarMisterio();

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
		mIndicator
				.setFillColor(getResources().getColor(R.color.ics_clear_grey)); // Cor
																				// de
																				// fundo
																				// do
																				// circulo
																				// da
																				// página
																				// visivel
		mIndicator.setStrokeColor(getResources().getColor(R.color.ics_black)); // Cor
																				// da
																				// circunferencia
																				// dos
																				// circulos
	}

	private void detectaPaginaCorrente() {

		// We set this on the indicator, NOT the mPager
		mIndicator
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {

						mPaginaActual = position;

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
/*
 * Refresca o conteudo das páginas de acordo com o mistério escolhido 
 */
		mOracao.clear();
		mOracao.addAll(Misterios.oracoesDoMisterio(mIndexDiaSemana,
				mMisterioSelected));
		mPager.getAdapter().notifyDataSetChanged();

		this.mPager.setCurrentItem(event.pagina);
	}

	public void onEvent(Estado event) {
		if (V.DEBUG) {
			Log.d(TAG, "Evento Estado recebido:" + event);
		}

		this.mIndexDiaSemana = event.getDiaSemana();
		this.mMisterioSelected = event.getMisterio();
		this.mPaginaActual = event.getPagina();

		startSignal.countDown();
		
		if (V.DEBUG) {
			Log.d(TAG, "onEvent(Estado event) startSignal=" + startSignal.getCount());
		}

		this.initDezena(); // Estado inicial obtido, iniciar a criação da ViewPageindicator 

	}
}
