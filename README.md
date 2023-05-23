# Fase II Desarrollo móvil - Modulo 2 Kotlin Intermedio
## Proyecto: aplicación para seguir progresos en el Gimnasio / de Ejercicios GYMSO

### Definición del Proyecto
***
Este proyecto está hecho para generar las pantallas de una aplicación para gestión personal de los avances en rutinas, por ejemplo, consultar ejercicios del día,
información nutrimental, noticias acerca de deportes, etc.

### Alcance del Proyecto
***
La finalidad de este proyecto es demostrar los conocimientos adquiridos durante el módulo II Kotlin Intermedio de Desarrollo Movil,
tomando la idea de un integrante del equipo y la desarrollamos basándonos en el lenguaje kotlin y a los conceptos aprendidos
durante cada sesión de la fase 2.

***Nota importante:*** durante el desarrollo del proyecto algunas partes del código tuvieron cambios significativos para poder
implementar temas que se vieron a lo largo del módulo.

### Integrantes:
* [Yael](https://github.com/YaelRmz)
* [Diego](https://github.com/n-DiegoTF)
* [Mario](https://github.com/marioquintalcob)
* [Erick](https://github.com/ErickDaniel04)

### Sesión 1
***
**Introducción a Android** <br>

Para nuestro proyecto sabemos que el nucleo de la apps es una administracion de avances, de acuerdo a lo visto en las sesiones dimensionaremos lo que se puede abarcar y que no para evitar
desecho de ideas pero teniendo en cuenta futuras implementaciones de ideas mas avanzadas. Durante su desarrollo se ha ido definiendo como va a interactuar el usuario con nuestra aplicacion,
es por ello que se realizaron esbozos de las pantallas.

Ejemplos de la funcion del boton login que se utilizo durante el desarrollo de nuestra aplicacion. NOTA: Se utilizaron datos estaticos sin embargo mas adelante se tiene en cuenta el uso de
conexiones y consultas a bases de datos reales.

```kotlin        
boton.setOnClickListener {
            // Actualice los correos con los reales del equipo.
            val usuarios = listOf(
                Pair("mario.e.quintal.cob@hotmail.com", "1234"),
                Pair("otro.usuario@example.com", "contrasena"),
                Pair("usuario3@example.com", "123456"),
                Pair("diego@gmail.com","1234"),
                Pair("n-diegotorres@hotmail.com", "1234"),
                Pair("yaelramirezmendez@gmail.com", "1234"),
                Pair("eguzmanh04@gmail.com", "1234")
            )

            val emailIngresado = email.text.toString()
            val passIngresada = pass.text.toString()

            var loginExitoso = false

            for (usuario in usuarios) {
                if (emailIngresado == usuario.first && passIngresada == usuario.second) {
                    loginExitoso = true
                    break
                }
            }

            if (loginExitoso) {
                // Pasa a la pantalla del Navigation Bar
                val bundle = Bundle()
                val intent = Intent(this, Navbar::class.java ).apply{
                    putExtras(bundle)
                }
                startActivity(intent)

            } else {
                Toast.makeText(this, "Datos incorrectos, favor de verificar", Toast.LENGTH_SHORT).show()
            }
        }
```
![imagen](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/bc1befb7-b8a2-4cb0-88f2-c70e9b502f9e)

### Sesión 2
***
**Views**

Para el uso de Views aprendido en la sesion 2, aplicamos a nuestro proyecto aquellos que creemos se acoplan
mejor a las problematicas de nuestro proyecto.

Por ejemplo, en el siguiente layout que es el inicial utilizamos un ImageView y dos botones los cuales envian a otro, dependiendo de la opción que eligió, 
ya sea registrarse o iniciar sesion.

![imagen](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/86c0fdc1-9647-43bd-8856-91efddb7550d)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnLogin"
        android:layout_width="194dp"
        android:layout_height="47dp"
        android:layout_marginTop="368dp"
        android:backgroundTint="@color/orange"
        android:text="@string/botonLogin"
        android:fontFamily="@font/sf_regular"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btnRegister"
        android:layout_width="194dp"
        android:layout_height="47dp"
        android:layout_marginBottom="184dp"
        android:backgroundTint="@color/orange"
        android:text="@string/botonRegistro"
        android:fontFamily="@font/sf_regular"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.487"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/btnLogin"
        app:layout_constraintVertical_bias="0.168" />

    <ImageView
        android:id="@+id/imgLogo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@+id/btnLogin"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.795"
        app:srcCompat="@drawable/muscle_logo"
        tools:srcCompat="@tools:sample/avatars" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Los botones realizan las siguientes funciones dependiendo cual sea el caso elegido por el usuario:

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton = findViewById(R.id.btnLogin)

        boton.setOnClickListener {
            val bundle = Bundle()

            val intent = Intent(this, LoginActivity::class.java ).apply{
                putExtras(bundle)
            }

            startActivity(intent)
        }

        boton2 = findViewById(R.id.btnRegister)

        boton2.setOnClickListener {
            val bundle = Bundle()

            val intent = Intent(this, RegisterActivity::class.java ).apply{
                putExtras(bundle)
            }

            startActivity(intent)
        }
    }

    private lateinit var boton: Button
    private lateinit var boton2: Button
}
```

Si el usuario elige iniciar sesion es enviado a esta pantalla:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".LoginActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@+id/txtEmail"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.501"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.721"
        app:srcCompat="@drawable/muscle_logo"
        tools:srcCompat="@tools:sample/avatars" />

    <EditText
        android:id="@+id/txtEmail"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"
        android:drawableLeft="@drawable/email_ic"
        android:drawablePadding="12dp"
        android:ems="10"
        android:fontFamily="@font/sf_regular"
        android:hint="email"
        android:inputType="textEmailAddress"
        app:layout_constraintBottom_toTopOf="@+id/txtPassword"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent" />

    <EditText
        android:id="@+id/txtPassword"
        android:layout_width="210dp"
        android:layout_height="46dp"
        android:layout_marginBottom="44dp"
        android:drawableLeft="@drawable/lock_ic"
        android:drawablePadding="12dp"
        android:ems="10"
        android:fontFamily="@font/sf_regular"
        android:hint="contraseña"
        android:inputType="textPassword"
        app:layout_constraintBottom_toTopOf="@+id/btnLogin2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/btnLogin2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="36dp"
        android:backgroundTint="@color/orange"
        android:fontFamily="@font/sf_regular"
        android:text="@string/botonLogin"
        app:layout_constraintBottom_toTopOf="@+id/txtReco"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <ImageView
        android:id="@+id/imageView6"
        android:layout_width="26dp"
        android:layout_height="31dp"
        android:layout_marginEnd="176dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="2.0"
        app:layout_constraintStart_toEndOf="@+id/imageView5"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.915"
        app:srcCompat="@drawable/gmail_icon" />

    <ImageView
        android:id="@+id/imageView5"
        android:layout_width="31dp"
        android:layout_height="30dp"
        android:layout_marginStart="168dp"
        android:layout_marginRight="64dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/imageView6"
        app:layout_constraintHorizontal_bias="2.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.913"
        app:srcCompat="@drawable/face_icon" />

    <TextView
        android:id="@+id/txtReco"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="104dp"
        android:fontFamily="@font/sf_regular"
        android:text="@string/olvidoContrasena"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Y si elige registrase es enviado a esta pantalla donde se le piden sus datos para completar dicho registro:

![imagen](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/5ade67f3-2671-49a0-bd4b-74ab1ab1bd48)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="@color/background"
    android:orientation="vertical"
    tools:context=".login.RegisterActivity">

    <include
        android:id="@+id/toolbar"
        layout="@layout/toolbar_register"/>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:orientation="vertical"
        android:background="@color/orange"
        >

        <ImageView
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:src="@drawable/add_perfil" />

        <TextView
            android:id="@+id/text_user_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:fontFamily="@font/jakarta_regular"
            android:text="Nombre de usuario"
            android:textColor="@color/white"
            android:textSize="24dp"
            android:textStyle="bold" />

        <View
            android:layout_width="match_parent"
            android:layout_height="1dp"
            android:layout_marginTop="16dp"
            android:background="@color/orange" />
    </LinearLayout>

    <!-- Label Datos generales -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:gravity="center_vertical"
        android:orientation="horizontal"
        android:paddingLeft="16dp"
        android:paddingRight="16dp">

        <ImageView
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:src="@drawable/ic_profile" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="8dp"
            android:fontFamily="@font/jakarta_regular"
            android:text="Completa tu registro"
            android:textColor="@color/black"
            android:textSize="18dp"
            android:textStyle="bold" />
        </LinearLayout>

    <!-- Input Nombre -->
        <com.google.android.material.textfield.TextInputLayout

            android:layout_width="match_parent"
            android:layout_height="56dp"
            android:layout_marginLeft="32dp"
            android:layout_marginRight="32dp"
            android:hint="Nombre y Apellidos">

            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/inputLayout1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="text"
                android:maxLines="1" />
        </com.google.android.material.textfield.TextInputLayout>
    <!-- Input Teléfono -->
        <com.google.android.material.textfield.TextInputLayout
            android:id="@+id/inputLayout2"
            android:layout_width="match_parent"
            android:layout_height="56dp"
            android:layout_marginLeft="32dp"
            android:layout_marginRight="32dp"
            android:hint="Telefono">

            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="text"
                android:maxLines="1" />
        </com.google.android.material.textfield.TextInputLayout>


    <!-- Input Correo -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/inputLayout3"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:layout_marginLeft="32dp"
        android:layout_marginRight="32dp"
        android:hint="Correo electrónico">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="text"
            android:maxLines="1" />
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Input Contraseña -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/inputLayout4"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:layout_marginLeft="32dp"
        android:layout_marginRight="32dp"
        android:hint="Contraseña"
        android:inputType="textPassword">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPassword"
            android:maxLines="1" />
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Input Confirmar contraseña -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/inputLayout5"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:layout_marginLeft="32dp"
        android:layout_marginRight="32dp"
        android:hint="Confirmar contraseña">

        <com.google.android.material.textfield.TextInputEditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPassword"
            android:maxLines="1"/>
    </com.google.android.material.textfield.TextInputLayout>

    <Button
        android:id="@+id/botonregistrar"
        android:layout_width="169dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:layout_marginLeft="100dp"
        android:backgroundTint="@color/orange"
        android:drawableLeft="@drawable/save_ic_white"
        android:fontFamily="@font/jakarta_regular"
        android:text="Crear cuenta"
        android:textColor="@color/white" />


</LinearLayout>
```

### Sesión 3

**Activities y Layouts**

**Layout**

En esta sesion aprendimos a como orientarse en la implementación de nuevas Activities y sus layouts. Para aplicar estos conceptos utilizamos en su mayoria el ConstraintLayout:
Ya que es el layout más nuevo de android que pretende reemplazar a los demás layouts mediante un sistema de relación entre views muy flexible y de fácil uso con la integración del _Layout Editor_.
Este layout es el último que los desarrolladores de google han creado y es el que viene por defecto al crear un proyecto con un Activity vacío. El objetivo de este es reemplazar los otros layouts para tener una organización plana (sin ViewGroups anidados).
La inclusión de este layout incluye muchos conceptos nuevos que facilitan el manejo de layouts complejos que puedan tener muchas relaciones con otros componentes. ConstraintLayout es parte la librería androidx, por lo que es necesaria la inclusión del repositorio maven de Google para su instalación.

Los constraints son relaciones restrictivas entre Views que determinan la posición de cada uno de estos (similar a las relaciones en los RelativeLayouts). Para definir la posición de un widget, este debe tener definido al menos un constraint vertical y otro horizontal.
El siguiente Layout que utiliza constraints es el que visualiza el usuario cuando requiere recuperar su contraseña olvidada.

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".RecopassActivity">

    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@+id/textView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:srcCompat="@tools:sample/avatars" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="44dp"
        android:text="@string/recuperar1"
        app:layout_constraintBottom_toTopOf="@+id/txtEmail2"
        app:layout_constraintEnd_toEndOf="parent"
        android:fontFamily="@font/sf_regular"
        app:layout_constraintHorizontal_bias="0.496"
        app:layout_constraintStart_toStartOf="parent" />

    <EditText
        android:id="@+id/txtEmail2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="20dp"
        android:ems="10"
        android:inputType="textEmailAddress"
        android:drawableLeft="@drawable/email_icon"
        app:layout_constraintBottom_toTopOf="@+id/btnPass"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/btnPass"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="256dp"
        android:text="@string/recuperar2"
        android:fontFamily="@font/sf_regular"
        android:backgroundTint="@color/orange"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```
![imagen](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/1cf3bee7-50bf-456f-a536-8dbdb0813e0f)

**Activity**

La navegación entre Activities es gestionada por un stack de Activities. Al correrse una nueva Activity, esta es puesta hasta la parte superior del stack y se mantiene ahí hasta que uno nuevo se manda a primer plano, dejando al anterior abajo de él.
Al finalizar una Activity, este se elimina del stack y reproduce un callback que podrá ser usado para manejar eventos.
Un activity tiene un ciclo de vida determinado, que comienza desde que el _Activity_ se abre por primera vez y termina al finalizarse por diversas causas (cerrar la aplicación como ejemplo).
El ciclo de vida se divide en varias etapas, en cada una de estas, el sistema reproduce un callback definido por nosotros a través de la sobreescritura de un método de la clase padre.
Estos callbacks son muy importantes, porque en cada uno de ellos podemos hacer la configuración de nuestra pantalla mediante la declaración de variables, inicialización de procesos,
asignación de la interfaz de usuario al _Activity_, el cierre de algún proceso, etc.

Si el usuario en el layout citado anteriormente ingresa un correo existente en la base de datos (se utiliza una lista estatica), el boton lo envia al Activity correspondiente al mensaje exitoso,
de lo contrario se muestra una notificacion "toast" indicando que hubo algún error en la información ingresada.

```kotlin
        boton.setOnClickListener {
            val emails = listOf("mario.e.quintal.cob@hotmail.com", "n-diegotorres@hotmail.com", "yaelramirezmendez@gmail.com", "eguzmanh04@gmail.com" )

            val emailIngresado = email.text.toString()

            var emailExistente = false

            for (e in emails) {
                if (emailIngresado == e) {
                    emailExistente = true
                    break
                }
            }

            if (emailExistente) {
                val bundle = Bundle()
                val intent = Intent(this, SendpassActivity::class.java ).apply{
                    putExtras(bundle)
                }
                startActivity(intent)

            } else {
                Toast.makeText(this, "Correo no registrado, favor de verificar", Toast.LENGTH_SHORT).show()
            }
        }
```

### Sesión 4
***
**Listas**

En esta sesion aprendimos como se establece el código que desplega listas scrolleables de elementos en la pantalla permitiendo la interacción con los elementos de las listas y tambien
a utilizar layouts personalizados para darles estilo.

```kotlin
class NewsProvider {
    companion object{
        val articulosList = listOf<Articulos>(
            Articulos("Giros rusos: qué son y cómo hacerlos",
            "Los giros rusos consisten en girar el torso, llevando de un lado a otro las manos y sin mover las piernas.",
            "Saber más...",
            "https://t1.uc.ltmcdn.com/es/posts/6/7/1/giros_rusos_que_son_y_como_hacerlos_53176_300_square.webp"
            ),
            Articulos("Ejercicios para quemar grasa abdominal",
                "Para descubrir los mejores ejercicios para quemar grasa abdominal te recomendamos que sigas leyendo este artículo.",
                "Saber más...",
                "https://t2.uc.ltmcdn.com/es/posts/6/1/0/ejercicios_para_quemar_grasa_abdominal_53016_300_square.webp"
            ),
            Articulos("Cómo hacer peso muerto",
                "Si estás decidido a tener una rutina de ejercicio e incluir el peso muerto, debes saber que se trata de una práctica muy completa pero que debe realizarse correctamente para evitar lesiones.",
                "Saber más...",
                "https://t2.uc.ltmcdn.com/es/posts/2/6/7/como_hacer_peso_muerto_51762_600.webp"
            ),
            Articulos("Cómo hacer press de banca",
                "Para poder hacer el press de banco correctamente, necesitarás una superficie donde poder apoyar toda la zona de la espalda y la cabeza.",
                "Saber más...",
                "https://t1.uc.ltmcdn.com/es/posts/1/0/7/como_hacer_press_de_banca_51701_600.webp"
            ),
            Articulos("Cómo hacer remo con barra",
                "Este ejercicio no requiere de mucha preparación previa y su ejecución podría recordar a la del peso muerto.",
                "Saber más...",
                "https://t2.uc.ltmcdn.com/es/posts/2/8/6/como_hacer_remo_con_barra_51682_600.webp"
            ),
            Articulos("Beneficios de hacer lagartijas",
                "Las lagartijas son ejercicios muy prácticos que se pueden realizar en casa sin más herramientas que nuestro propio cuerpo y una superficie plana.",
                "Saber más...",
                "https://t2.uc.ltmcdn.com/es/posts/9/3/7/beneficios_de_hacer_lagartijas_50739_600.webp"
            ),
            Articulos("Ejercicios para bíceps y tríceps",
                "Para ganar masa y resistencia muscular en los brazos te traemos los mejores ejercicios para bíceps y tríceps.",
                "Saber más...",
                "https://t1.uc.ltmcdn.com/es/posts/9/6/2/ejercicios_para_biceps_y_triceps_50269_600.webp"
            ),
            Articulos("Ejercicios para fortalecer la espalda",
                "La espalda es una de las regiones del cuerpo más importantes, pues se compone de huesos, músculos y otros tejidos que van desde el cuello hasta la cintura pélvica",
                "Saber más...",
                "https://t2.uc.ltmcdn.com/es/posts/1/8/5/ejercicios_para_fortalecer_la_espalda_49581_600.webp"
            ),
        )
    }
}
```

En las siguientes clases podemos observar como se implementa en el proyecto un RecyclerView el cual es una mejora de los ListAdapters, reciclando Views y métodos para ahorrar recursos.
En este ejemplo creamos uno desde cero, incluyendo su Adapter.

Adapter
```kotlin
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.news.model.Articulos

class NewsAdapter(private val comunityList:List<Articulos>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(layoutInflater.inflate(R.layout.card_news, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = comunityList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = comunityList.size

}
```

ViewHolder
```kotlin
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.news.model.Articulos

class NewsViewHolder(view: View):RecyclerView.ViewHolder(view){

    val titulo = view.findViewById<TextView>(R.id.comunityTitle)
    val texto = view.findViewById<TextView>(R.id.comunityTexto)
    val url = view.findViewById<TextView>(R.id.comunityURL)
    val foto = view.findViewById<ImageView>(R.id.comunityImg)

    fun render(comunityModel: Articulos){
        titulo.text = comunityModel.titulo
        texto.text = comunityModel.descripcion
        url.text = comunityModel.url
        Glide.with(foto.context).load(comunityModel.foto).into(foto)
    }

}
```
![imagen](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/19f89b13-39b0-4cd4-b173-fc6179e418b4)

### Sesión 5
**Fragments**

En esta sesion aprendimos que como el nombre lo indica, un Fragment es un fragmento de la UI que implementa tanto un ciclo de vida y eventos propios.
En términos prácticos, nos podríamos referir a un Fragment como una especie de Activity que puede ser reutilizada e insertada en otras Activities como si de un View se tratase.
Un Fragment siempre está asignado dentro de una Activity especial: un FragmentActivity y esta puede contener varios Fragments para dividir nuestra interfaz en varias porciones,
formando diferentes configuraciones dependientes de factores como la orientación de la pantalla, su resolución, etc.

En nuestro proyecto utilizamos una actividad que contiene el siguiente FrameLayout
```xml
    <FrameLayout
        android:id="@+id/frame_layout"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/bottomNavigationView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

    </FrameLayout>
```
El cual mediante codigo se comunica con 4 diferentes Fragments, y mostrara el correspondiente a la eleccion del usuario:
```kotlin
class Navbar : AppCompatActivity() {

    private lateinit var binding: ActivityNavbarBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityNavbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId){

                R.id.home -> replaceFragment(Home())
                R.id.comunidad -> replaceFragment(Comunity())
                R.id.nutricion -> replaceFragment(Nutrition())
                R.id.perfil -> replaceFragment(Profile())

                else -> {


                }

            }
            true

        }

    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }

}
```
![imagen](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/1a5bdf57-08e3-47cc-aa35-a63f0a200988)

Siguiendo con el tema de fragments, el siguiente se implemento para el uso de un recyclerview:
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="#f1c15d"
    tools:context=".routines.Rutinas">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="Routines Fragment"
        android:textSize="26dp"
        android:gravity="center"
        />
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="20dp"
        android:layout_marginLeft="20dp"
        android:layout_marginTop="36dp"
        android:text="EJERCICIOS"
        android:textStyle="bold"
        android:textSize="30sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="20dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        tools:listitem="@layout/card_layout"
        />

</FrameLayout>
```
### Sesión 6
***
**Material Design**

En esta seccion aprendimos que Material Design es un sistema que envuelve a una guia de estilo de diseño, herramientas y componentes para aplicaciones móviles y web.
La base del concepto de este es que toda la interfaz debe ser representado por materiales, (específicamente papel y tinta) y añade conceptos visuales como textura,
representación de luces y sombras o altura.

En nuestro proyecto implementamos material design cargando la libreria de soporte en el archivo build.gradle(App) con la siguiente sintaxis:

```kotlin
implementation 'com.google.android.material:material:1.5.0'
```

El cual se carga de la siguiente forma en el tema a utilizar en nuestro proyecto:
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Base.Theme.Proyecto01Equipo16" parent="Theme.Material3.DayNight.NoActionBar">
        <!-- Customize your light theme here. -->
        <!-- <item name="colorPrimary">@color/my_light_primary</item> -->
    </style>

    <style name="Theme.Proyecto01Equipo16" parent="Base.Theme.Proyecto01Equipo16" />
</resources>
```
Las CardViews se implementaron en el Home:
```kotlin
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import org.bedu.proyecto01equipo16.R
import org.bedu.proyecto01equipo16.databinding.FragmentHomeBinding

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            for (viewE in group.children) {
                val radio = viewE as RadioButton
                radio.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
                radio.setTextColor(ContextCompat.getColor(view.context, R.color.black))
            }

            val radioButton = view.findViewById<RadioButton>(checkedId)
            radioButton.background = ContextCompat.getDrawable(view.context, R.drawable.orange_circle_drawable)
            radioButton.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
```

![1684805274404](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/e1e313b0-85b7-4297-8a53-e81dc5e6c290)


### Sesión 7
***
**Menús**

En esta sesion vimos lo relacionado a los menús. En Android existen tres tipos de menú, Popup, contextual y de opciones. Cada menú debe tener un archivo XML relacionado a él, este definirá su Layout.
Así como hemos visto en las clases, el elemento correspondiente a un menú en XML será <menu…/> y sus elementos internos serán <items…./>.

El menu utilizado en nuestra aplicación, se encuentra en la parte inferior:
```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:title="Inicio"
        android:id="@+id/home"
        android:fontFamily="@font/jakarta_regular"
        android:icon="@drawable/ic_home"
        />
    <item android:title="News"
        android:id="@+id/comunidad"
        android:fontFamily="@font/jakarta_regular"
        android:icon="@drawable/news_icon"
        />
    <item
        android:id="@+id/rutinas"
        android:icon="@drawable/gym_icon"
        android:title="Rutinas" />

    <item android:title="Perfil"
        android:id="@+id/perfil"
        android:icon="@drawable/user_icon"
        />
</menu>
```
[device-2023-05-22-120033.webm](https://github.com/marioquintalcob/Proyecto01Equipo16/assets/119343518/e1800041-07ae-4af5-b9f8-a55f9c542dd5)

### Sesión 8
***
**Gradle y Preparación para lanzamiento**

En esta sesion vimos la informacion relacionada con configurar gradle para el lanzamiento, las Build variants que son diferentes versiones de una aplicación y la Firma de aplicacion que es
generar un apk de prueba, para instalarlo en un dispositivo, compilando un archivo .aab y probando su funcionalidad mediante buildtool para que finalmente se firme la aplicación de release y se pueda testear en búsqueda de algún defecto.

**Build variants**

En el build.gradle (:app) se implemento lo siguiente:
```kotlin
    buildTypes {
        debug {
            applicationIdSuffix ".debug"
            debuggable true
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
```
