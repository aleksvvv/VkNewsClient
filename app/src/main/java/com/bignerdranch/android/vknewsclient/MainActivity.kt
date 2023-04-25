package com.bignerdranch.android.vknewsclient

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.android.TextLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bignerdranch.android.vknewsclient.ui.theme.MainScreen
import com.bignerdranch.android.vknewsclient.ui.theme.PostVcCard
import com.bignerdranch.android.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            VkNewsClientTheme {
//                // A surface container using the 'background' color from the theme
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colors.background)
//                        .padding(8.dp)
//                ) {
////PostVcCard()
//                }
//            }
            VkNewsClientTheme {
                MainScreen()
            }

        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun test() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TopAppBar")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(selected = true,
                    onClick = { },
                    icon = {
                        Icon(Icons.Filled.Call, contentDescription = null)
                    },
                    label = { Text(text = "Call") }
                )
                BottomNavigationItem(selected = true,
                    onClick = { },
                    icon = {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    },
                    label = { Text(text = "Add") }
                )
            }
        },
        drawerContent = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "drawerContent")
            }
            Text(text = "gdsgsgtfdsa")
        }


//    topBar = TopAppBar(
//        title = {
//            Text(text = "TopAppBar")
//        },
//        navigationIcon = {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(Icons.Filled.Menu , contentDescription = null)
//            }
//        }
//            )

    ) {
        Text(text = "This is scaffold")

    }

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Example3()
//    }
}

@Composable
private fun Example() {
    OutlinedButton(onClick = { /*TODO*/ }) {
        Text(
            text = "Outlined Button"
        )
    }
}

@Composable
private fun Example2() {
    TextField(value = "Value", onValueChange = {},
        label = { Text(text = "Label") })
}

@Composable
private fun Example3() {
    AlertDialog(onDismissRequest = { /*TODO*/ }, title = {
        Text(text = "Are you sure?")
    },
        text = {
            Text(text = "trgcbgxftftrt?")
        },
        confirmButton = {
            Text(text = "Confirm")
        },
        dismissButton = {
            Text(text = "Dismiss")
        }
    )
}