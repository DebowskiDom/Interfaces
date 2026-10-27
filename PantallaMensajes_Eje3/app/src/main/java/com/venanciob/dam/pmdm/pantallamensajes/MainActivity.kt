package com.venanciob.dam.pmdm.pantallamensajes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


data class Mensaje(
    val autor: String,
    val texto: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF397D68),
                    background = Color(0xFFF8F8F0),
                    surface = Color(0xFFFFFEF8),
                    surfaceVariant = Color(0xFFECEBE5),
                    primaryContainer = Color(0xFFD9EDE1)
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaMensajes()
                }
            }
        }
    }
}

@Composable
fun PantallaMensajes() {

    val mensajes = listOf(
        Mensaje(
            "Laura",
            "¿Qué tal ha ido la Qué tal ha ido la claseQué tal ha ido la claseQué tal ha ido la claseQué tal ha ido la claseQué tal ha ido la claseQué tal ha ido la clase?"
        ),
        Mensaje(
            "Mario",
            "Ya terminé la práctica de Compose.Ya terminé la práctica de Compose.Ya terminé la práctica de Compose.Ya terminé la práctica de Compose.Ya terminé la práctica de Compose."
        ),
        Mensaje(
            "Ana",
            "Mañana llevo el proyecto.Mañana llevo el proyectoMañana llevo el proyectoMañana llevo el proyectoMañana llevo el proyectoMañana llevo el proyecto"
        ),
        Mensaje(
            "Pedro",
            "Nos vemos mañana.Nos vemos mañana.Nos vemos mañana.Nos vemos mañana.Nos vemos mañana.Nos vemos mañana.Nos vemos mañana.Nos vemos mañana."
        ),
        Mensaje(
            "Sara",
            "He subido el ejercicio.He subido el ejercicio.He subido el ejercicio.He subido el ejercicio.He subido el ejercicio.He subido el ejercicio.He subido el ejercicio."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 48.dp
            )
    ) {
        Text(
            text = "MIS MENSAJES",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(mensajes) { mensaje ->
                TarjetaMensaje(
                    mensaje = mensaje
                )
            }
        }
    }
}


@Composable
fun TarjetaMensaje(
    mensaje: Mensaje,
    modifier: Modifier = Modifier
) {
    var expandido by remember {
        mutableStateOf(false)
    }

    val colorFondo by animateColorAsState(
        targetValue = if (expandido) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        },
        label = "colorMensaje"
    )

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = mensaje.autor.first().uppercase(),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        expandido = !expandido
                    }
                    .animateContentSize()
            ) {
                Text(
                    text = mensaje.autor,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier.animateContentSize(),
                    shape = MaterialTheme.shapes.medium,
                    color = colorFondo,
                    shadowElevation = 2.dp
                ) {
                    Text(
                        text = mensaje.texto,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = if (expandido) {
                            Int.MAX_VALUE
                        } else {
                            1
                        },
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClaro(){
    MaterialTheme {
        PantallaMensajes()
    }
}

