package com.example.revisao_mobile

// MainActivity.kt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.material3.icons.Outlined
import androidx.compose.material3.icons.filled.ArrowBack
import androidx.compose.material3.icons.filled.Save
import androidx.compose.material3.icons.filled.TextFields
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.featherandroidtasks.ui.theme.FeatherAndroidTasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeatherAndroidTasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val selectedTab = remember { mutableStateOf(0) }

    val tabs = listOf(
        "Home" to Icons.Default.Person,
        "Alunos" to Icons.Default.TextFields,
        "Cursos" to Icons.Default.School
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = tabs[selectedTab.value].first) }
            )
        },
        bottomBar = {
            BottomNavigation {
                tabs.forEachIndexed { index, (title, icon) ->
                    BottomNavigationItem(
                        selected = index == selectedTab.value,
                        onClick = { selectedTab.value = index },
                        icon = { Icon(icon, contentDescription = title) },
                        label = { Text(title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Content
        when (selectedTab.value) {
            0 -> HomeContent()
            1 -> AlunoFormContent()
            2 -> CursoFormContent()
        }
    }
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bem-vindo à tela principal!")
    }
}

@Composable
fun AlunoFormContent() {
    FormContent(
        title = "Cadastro de Aluno",
        icon = Icons.Default.Person
    ) {
        // Implementação do formulário de cadastro de aluno aqui
    }
}

@Composable
fun CursoFormContent() {
    FormContent(
        title = "Cadastro de Curso",
        icon = Icons.Default.School
    ) {
        // Implementação do formulário de cadastro de curso aqui
    }
}

@Composable
fun FormContent(
    title: String,
    icon: ImageVector,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
            Text(text = title, style = MaterialTheme.typography.h6)
            Icon(imageVector = icon, contentDescription = null)
        }
        // Conteúdo do formulário
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FeatherAndroidTasksTheme {
        MyApp()
    }
}
