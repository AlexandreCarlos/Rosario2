/**
 * 
 */
package pt.carlos.alex.rosario;

import android.text.Html;
import android.text.Spanned;

/**
 * @author alexandre
 *
 */
public final class Oracoes {
	
	private static final String PAI_NOSSO = 
	"<html><head><title>Pai Nosso</title></head><body><h1>Pai Nosso</h1>Pai Nosso que estais no céu,<br />santificado seja o vosso nome,<br />vem a nós o vosso reino,<br />seja feita a vossa vontade<br />assim na terra como no céu.<br /><br />O pão nosso de cada dia nos daí hoje,<br />perdoai-nos as nossas ofensas,<br />assim como nós perdoamos<br />a quem nos tem ofendido,<br />não nos deixei cair em tentação<br />mas livrai-nos do mal.<br /><br />Amen<br /><hr /></body></html>";

	private static final String AVE_MARIA =
	"<html><head><title>Avé Maria</title></head><body><h1>Avé Maria</h1>Ave Maria,<br />cheia de graça,<br />o Senhor é convosco,<br />bendita sois Vós entre as mulheres,<br />bendito é o fruto em Vosso ventre,<br />Jesus.<br /><br />Santa Maria Mãe de Deus,<br />rogai por nós os pecadores,<br />agora e na hora da nossa morte.<br /><br />Amen<br /></body></html>";
	
	private static final String GLORIA = 
	"<html><head><title>Glória</title><body><h1>Glória</h1>Glória ao Pai,<br />e ao Filho e ao Espírito Santo,<br />assim como era no princípio,<br />agora e sempre.<br /><br />Amen<br /></body></html>";
	
	private static final String SALVE_RAINHA =
	"<html><head><title>Salve Rainha</title></head><body><h1>Salve Rainha</h1>Salve Rainha,<br />Mãe de Misericórdia,<br />vida e doçura esperança nossa salve!<br />A vós bradamos degredados filho de Eva.<br />A vós suspiramos gemendo e chorando neste<br />vale de lágrimas.<br />Eia pois advogada nossa<br />esses vossos olhos misericordiosos a nós volvei,<br />e depois deste desterro mostrai Jesus bendito fruto em vosso ventre,<br /><br />ó clemente,<br />ó piedosa<br />ó doce e Santa Virgem Maria.<br /><br />Rogai por nós Santa mãe de Deus.<br />Para que sejamos sempre livre do pecado,<br />protegido de todos os perigos<br />e dignos da promessa de Cristo.<br /><br />Amen<br /></body></html>";
	
	private static final String JACULATORIAS = 
			"<html><head><title>Jaculatórias</title></head><body><h1 style=\"text-align: center; color: #0000FF;\">Jaculatórias</h1>Ó Maria concebida sem pecado<br />Rogai por nós que recorremos a vós.<br />Ó meu Jesus, perdoai-nos e livrai-nos do fogo do inferno <br />Levai as almas todas para o Céu, principalmente as que mais precisarem.</body></html>";
	
	public final static Spanned PaiNosso () {
		return Html.fromHtml(PAI_NOSSO);
	}
	
	public final static Spanned AveMaria () {
		return Html.fromHtml(AVE_MARIA);
	}
	
	public final static Spanned Gloria () {
		return Html.fromHtml(GLORIA);
	}
	
	public final static Spanned SalveRainha () {
		return Html.fromHtml(SALVE_RAINHA);
	}
	
	public final static Spanned Jaculatorias () {
		return Html.fromHtml(JACULATORIAS);
	}
}
