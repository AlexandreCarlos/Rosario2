/*
 * Copyright (C) 2012 Alexandre Carlos 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Fragment que mostra as orações associadas a um mistério
 */
package pt.carlos.alex.rosario;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import android.support.v4.view.ViewPager;
import android.util.Log;

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
public class MostraOracoes extends SherlockFragment {

	private static final String TAG = "Rosário.MostraOracoes";

	private EventBus mEventBus;
	private boolean mRegistado = false;

	// Controlo da ocorrência das condições de inicialização do Page Viewer.
	private final CountDownLatch startPageViewer = new CountDownLatch(2);

	@ViewById(R.id.pager)
	protected ViewPager mPager;

	@ViewById(R.id.indicator)
	ContasRosario mIndicator;

	// @ViewById(R.id.textMisterio)
	// TextView mTextMisterio;

	protected int mIndexDiaSemana = -1;
	protected int mMisterioSelected = 0;
	public int mPaginaActual = 0;

	protected List<String> mOracao;
	protected List<Integer> mCoresContas;

	/**
	 * Inicialização e registo no event bus.
	 */
	@AfterInject
	void beforeCreate() {
		mEventBus = EventBus.getDefault();

		registaBus();

		if (V.DEBUG) {
			Log.d(TAG, "Inicializa-mIndexDiaSemana:" + mIndexDiaSemana
					+ "; mMisterioSelected:" + mMisterioSelected
					+ "; mPaginaActual:" + mPaginaActual);
			Log.d(TAG, "beforeCreate.startSignal=" + startPageViewer.getCount());
		}
	}

	/**
	 * Ativação da condição de View (group) inicializada.
	 */
	@AfterViews
	void afterCreate() {

		try {

			startPageViewer.countDown();

			if (V.DEBUG) {
				Log.d(TAG,
						"afterCreate.startSignal=" + startPageViewer.getCount());
			}
		} catch (Exception e) {
			Log.e(TAG, "Erro no init() @AfterViews:", e);
		}

	}

	/**
	 * Método auxiliar de controlo do registo no event bus.
	 */
	private void registaBus() {
		if (!mRegistado) {
			mEventBus.register(this);
			mRegistado = true;
		}
	}

	/**
	 * Método auxiliar de controlo do desregisto no event bus.
	 */
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

	/**
	 * 
	 * Processo background de inicialização das orações associadas ao mistério
	 * selecionado.
	 * 
	 */
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
				Log.d(TAG, "initDezena before.await.startSignal="
						+ startPageViewer.getCount());
			}

			startPageViewer.await(); // Espera pela conclusão da inicialização
										// do
										// estado e das Views

			if (V.DEBUG) {
				Log.d(TAG, "initDezena after.await.startSignal="
						+ startPageViewer.getCount());
			}

			mOracao = Misterios.oracoesDoMisterio(mIndexDiaSemana,
					mMisterioSelected);

			geraPageView();

		} catch (InterruptedException e) {
			Log.e(TAG, "initDezena startPageViewer await Interrupted", e);
		}
	}

	// /**
	// * Método auxiliar que identifica o tipo de mistério do dia.
	// */
	// private void identificarMisterio() {
	//
	// mTextMisterio.setText(Misterios.identificarMisterioDia(mIndexDiaSemana,
	// mMisterioSelected));
	//
	// }

	/**
	 * Método auxiliar gerador da View Page com as orações associadas ao
	 * mistério selecionado.
	 */
	@UiThread
	protected void geraPageView() {

		// identificarMisterio();

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

	/**
	 * Definição do Page Change Listener para as mudanças de página na View
	 * Page.
	 */
	private void detectaPaginaCorrente() {

		// We set this on the indicator, NOT the mPager
		mIndicator
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(final int position) {

						mPaginaActual = position;

						mEventBus.post(new Pagina(position));

						if (V.DEBUG) {
							Log.d(TAG, "onPageSelected-position:" + position);
							Log.d(TAG, "Página (Integer) mEventBus generated");
						}
					}

					@Override
					public void onPageScrolled(final int position,
							final float positionOffset,
							final int positionOffsetPixels) {
					}

					@Override
					public void onPageScrollStateChanged(final int state) {
					}
				});
	}

	/**
	 * Trata da mudança de Mistério, notificado pelo evento Rezar.
	 */
	@SuppressWarnings(value = "UnusedDeclaration")
    public void onEvent(final Rezar event) {

		try {
			if (V.DEBUG) {
				Log.d(TAG, "Evento Rezar recebido:" + event);
			}

			mIndexDiaSemana = event.diaSemana;
			mMisterioSelected = event.misterio;

			/*
			 * Refresca o conteudo das páginas de acordo com o mistério
			 * escolhido
			 */
			mOracao.clear();
			mOracao.addAll(Misterios.oracoesDoMisterio(mIndexDiaSemana,
					mMisterioSelected));

			if (mPager.getAdapter() != null) {
				this.mPager.getAdapter().notifyDataSetChanged();
				this.mPager.setCurrentItem(event.pagina);
			}

		} catch (Exception e) {
			Log.e(TAG, "Erro no onEvent(final Rezar event):", e);
		}
	}

	/**
	 * Recebe a notificação do estado da aplicação e ativa a condição de estado
	 * inicial da aplicação definido.
	 */
	@SuppressWarnings(value = "UnusedDeclaration")
    public void onEvent(final Estado event) {
		if (V.DEBUG) {
			Log.d(TAG, "Evento Estado recebido:" + event);
		}

		this.mIndexDiaSemana = event.getDiaSemana();
		this.mMisterioSelected = event.getMisterio();
		this.mPaginaActual = event.getPagina();

		startPageViewer.countDown();

		if (V.DEBUG) {
			Log.d(TAG, "onEvent(Estado event) startPageViewer="
					+ startPageViewer.getCount());
		}

		this.initDezena(); // Estado inicial obtido, iniciar a criação da
							// ViewPageindicator

	}
}
