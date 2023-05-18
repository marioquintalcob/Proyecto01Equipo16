# Fase III Desarrollo móvil - Kotlin Intermedio
## Proyecto: aplicación para seguir progresos en el Gimnasio / de Ejercicios

### Definición del Proyecto
***
Este proyecto está hecho para generar las pantallas de una aplicación para gestión personal de los avances en rutinas, por ejemplo, consultar ejercicios del día,
información nutrimental, noticias acerca de deportes, etc.

### Alcance del Proyecto
***
La finalidad de este proyecto es demostrar los conocimientos adquiridos durante el módulo III de Desarrollo Movil,
tomando la idea de un integrante del equipo y la desarrollamos basándonos en el lenguaje kotlin y a los conceptos aprendidos
durante cada sesión de la fase 3.

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
### Sesión 2
***
**Views**

Para el uso de Views aprendido en la sesion 2, aplicamos a nuestro proyecto aquellos que creemos se acoplan
mejor a las problematicas de nuestro proyecto.

Por ejemplo, en el siguiente layout que es el inicial utilizamos un ImageView y dos botones los cuales envian a otro, dependiendo de la opción que eligió, 
ya sea registrarse o iniciar sesion.

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
Y si elige registrase es enviado a esta pantalla donde, cuando la funcionalidad este completa podra utilizar sus credenciales de Facebook o Gmail para completar dicho registro:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".RegisterActivity">

    <TextView
        android:id="@+id/textView3"
        android:layout_width="235dp"
        android:layout_height="71dp"
        android:text="@string/registrese"
        android:textAlignment="center"
        android:fontFamily="@font/sf_regular"
        android:textAppearance="@style/TextAppearance.AppCompat.Large"
        android:textColor="@color/orange"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.219" />

    <TextView
        android:id="@+id/textView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/inicia"
        android:fontFamily="@font/sf_regular"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.428" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/orange"
        android:drawableLeft="@drawable/google_small"
        android:fontFamily="@font/sf_regular"
        android:text="@string/google"
        android:textColor="@color/white"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.512" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:backgroundTint="@color/orange"
        android:drawableLeft="@drawable/facebook_small"
        android:fontFamily="@font/sf_regular"
        android:text="@string/facebook"
        android:textColor="@color/white"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.497"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.644" />

    <TextView
        android:id="@+id/textView5"
        android:layout_width="342dp"
        android:layout_height="37dp"
        android:fontFamily="@font/sf_regular"
        android:text="@string/terminos"
        android:textAlignment="center"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.492"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.949" />
</androidx.constraintlayout.widget.ConstraintLayout>
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
ejemplo de lista en el proyecto.
```

En las siguientes clases podemos observar como se implementa en el proyecto un RecyclerView el cual es una mejora de los ListAdapters, reciclando Views y métodos para ahorrar recursos.
En este ejemplo creamos uno desde cero, incluyendo su Adapter.

```kotlin
ejemplo de RecyclerView en el proyecto.
```
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
### Sesión 7
***
**Menús**

En esta sesion vimos lo relacionado a los menús. En Android existen tres tipos de menú, Popup, contextual y de opciones. Cada menú debe tener un archivo XML relacionado a él, este definirá su Layout.
Así como hemos visto en las clases, el elemento correspondiente a un menú en XML será <menu…/> y sus elementos internos serán <items…./>.
```kotlin
Implementacion de menus en nuestro proyecto.
```
### Sesión 8
***
**Gradle y Preparación para lanzamiento**

En esta sesion vimos la informacion relacionada con configurar gradle para el lanzamiento, las Build variants que son diferentes versiones de una aplicación y la Firma de aplicacion que es
generar un apk de prueba, para instalarlo en un dispositivo, compilando un archivo .aab y probando su funcionalidad mediante buildtool para que finalmente se firme la aplicación de release
y se pueda testear en búsqueda de algún defecto.

```kotlin
Implementacion de Gradle y Preparación para lanzamiento en nuestro proyecto.
```
**Configuración de gradle**
```kotlin
Configuración de gradle en nuestro proyecto.
```
**Build variants**
```kotlin
Build variants en nuestro proyecto.
```
**Firma de aplicación**
```kotlin
Firma de aplicación en nuestro proyecto.
```
