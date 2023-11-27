package com.example.ejercicios_clase

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import java.util.Locale

class ChistesActivity: AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var chisteText: TextView
    private lateinit var chisteBt: Button
    private lateinit var vozChistes: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chistes)

        asociarElementos()
        cargarEventos()

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        if (vozChistes.isSpeaking) {
            vozChistes.stop()
        }
        vozChistes.shutdown()

        super.onDestroy()
    }
    fun asociarElementos(){
        chisteText = findViewById(R.id.chisteText)
        chisteBt = findViewById(R.id.chisteBt)

        vozChistes = TextToSpeech(this, this)
    }

    fun cargarEventos(){
        chisteBt.setOnClickListener {
            chisteText.typeface = ResourcesCompat.getFont(this, R.font.lost_emerald_font)
            val chiste = ListaChistes.mostrarChiste()
            chisteText.text = chiste
            vozChistes.speak(chiste, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Establecer el idioma (en este caso, español)
            val result = vozChistes.setLanguage(Locale("es", "ES"))

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                // Idioma no soportado o datos de idioma faltantes
                // Manejar según sea necesario
            } else {
                val textoLargo =  "Como veo que hay personas que no saben de la existencia de esta gran película, procederé a explicar el contexto de este pequeño pero increíble post el cual ha enviado nuestra amiga y allegada Lorena, la cual adjunta con el mismo un mensaje corto claro y conciso, Como?, que denota un desconocimiento de su significado. Para entender correctamente y en su plenitud dicho contexto debemos conocer unas nociones básicas de su trama. Joseph Cooper, o mejor conocido a lo largo de la película, Cooper, el más habilidoso piloto que la NASA ha albergado en sus filas aún sin salir de nuestro Planeta, La Tierra, debido a un fatídico accidente al salir de la atmósfera con una nave provocado por las medidas preventivas de una máquina, hecho el cual desembocará en una actitud antipática por parte de Cooper hacia las máquinas de apoyo que utiliza la NASA en sus expediciones, los marines. Este siniestro provoca un trauma a nuestro protagonista, el cual sufre recurrentemente de la misma pesadilla con dicho dia como foco principal. Nuestra historia comienza con el repentino despertar de Cooper tras una de estas pesadillas, se reincorpora y baja a comer con su familia a desayunar. En el comedor esperan, su suegro, Donald y sus dos hijos, Tom y Murph respectivamente, siendo esta última una pieza clave de nuestra historia, pero ésto se desvelará más adelante. Terminan de desayunar y la película procede a mostrarnos pequeñas pinceladas sobre el día a día de nuestros protagonistas, sus personalidades y aún mas importante si cabe, el estado deplorable y decadente del planeta Tierra, debido al reiterado desgaste por parte de los humanos. Recurrentes tormentas de arena, problemas con la capa de ozono, invasión del dióxido de carbono sobre el oxígeno y por ende dificultad a la hora de producir y manufacturar alimentos son algunas de los infinitas consecuencias que hemos ido dejando a nuestro paso por la incesante explotación de la materia prima de nuestro hogar. Tras una de las miles de tormentas de arena que azotaban a la sociedad y por una aparente casualidad, Cooper se percata de una anomalia gravitatoria localizada en el cuarto de su hija Murph. Esta anomalía resultan ser unas coordenadas hacía un lugar aun desconocido por nuestros protagonistas las cuales, más tarde, se dan a conocer como el escondite donde la NASA investiga en secreto, por el claro descontento general de la población si se llegara a revelar el gasto que conlleva una inversión en exploración espacial con el planeta en su estado más deplorable económica y socialmente. El antiguo mentor de Cooper, el profesor Brand, le propone una petición al mismo, liderar un grupo de valientes astronautas inexpertos en misiones de campo a rescatar a 10 valerosos hombres que tiempo atras se abarcaron en otra misión llamada las las misiones Lázaro, haciendo referencia al personaje Biblico que resucitó entre los muertos. el objetivo 10 astronautas era explorar, recolectar y enviar los datos de diferentes planetas de otro sistema solar el cual estaba desde hace unos años a nuestro alcance debido a la repentina aparición de un misterioso agujero de gusano que les transportaría a dicho sistema repleto de planetas con visibles indicios de características que facilitarían la vida en los mismos. El único pero gran inconveniente de este sistema solar era la inevitable cohabitación con un agujero negro, Gargantua, el cual por su gran masa, tamaño y cercanía a nuestros posibles futuros planetas produciría un desfase temporal considerable en alguno de los mismos. Uno de dichos planetas, concretamente el más próximo a Gargantua se le conoce en la película como \"El Planeta De Miller\", ya que dicho planeta habia sido asignado a uno de los astronautas enviados en las misiones Lázaro a Miller. Este personaje aparentemente mantiene una relación con una de las integrantes de la misión que debe liderar nuestro protagonista Cooper, Amelia Brand, hija del doctor Brand, la cual sera un importe personaje en la posible secuela de la película. Dicha información no es relevante para el entendimiento de este post pero si una recomendación personal a conocer de un servidor junto con el resto de la película. Volviendo al tema que nos ocupa, el planeta de Miller, dicho planeta debido a la cercanía con Gargantua, poseé un desfase temporal muy grande. Para que te hagas una idea aproximada, cada hora allí equivaldría a 7 años en la Tierra. Conociendo esta información podemos deducir que por cada año que transcurre en nuestro planeta son solo 8 minutos y medio los que pasan en el planeta de Miller. Si tenemos en cuenta que Interestellar se estrenó en 2014 y actualmente vivimos en el año 2023 concluimos que han pasado 9 años desde el nacimiento de la mejor película jamas creada en todos los tiempos hasta hoy y haciendo la conversión de tiempo entre los dos planetas sabemos que el tiempo exacto transcurrido en el planeta de Miller es 1 hora y 17 minutos. Esta es la explicación del contexto del anterior post, un cordial saludo"
                val maxLength = 4000 // Longitud máxima de texto permitida
                var inicio = 0

                while (inicio < textoLargo.length) {
                    val fin = minOf(inicio + maxLength, textoLargo.length)
                    val fragmento = textoLargo.substring(inicio, fin)

                    // Léelo en voz alta
                    vozChistes.speak(fragmento, TextToSpeech.QUEUE_ADD, null, null)

                    inicio = fin
                }
            }
        }
    }


}