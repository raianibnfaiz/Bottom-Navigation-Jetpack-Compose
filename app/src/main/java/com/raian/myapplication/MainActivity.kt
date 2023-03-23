package com.raian.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raian.myapplication.model.Pet
import com.raian.myapplication.ui.theme.BottomNavigationJetpackComposeTheme


sealed class Destination(val route: String){

    object Detail: Destination("detail/{elementId}")
    fun createRoute(id:Int) = "detail/${id}"

    object Animal: Destination("pet/{animalId}")
    fun animalRoute(id:Int) = "pet/${id}"
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
@Composable
fun Home() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Home")


    }

}
@Composable
fun Notifications() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Notifications")

        }
    }
}

@Composable
fun Settings(navigateToProfile: (Pet) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Settings")

        }
    }
}

@Composable
fun Accounts() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Accounts")

        }
    }
}
@Composable
fun NavigationController(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {

        composable(NavigationItem.Home.route) {
            Home()
        }

        composable(NavigationItem.PetScreen.route) {
            PetScreen(navController)
        }

        composable(NavigationItem.Settings.route) {
            Setting(navController)
        }

        composable(NavigationItem.Profile.route) {
            Profile()
        }
        composable(Destination.Detail.route){navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if(elementId == null){
                Toast.makeText(context, "Element id is required", Toast.LENGTH_SHORT).show()
            }
            else{
                Detail(elementId = elementId.toInt())
            }
        }
        composable(Destination.Animal.route){
            navBackStackEntry ->
            val animal = navBackStackEntry.arguments?.getString("animalId")
            if(animal == null){
                Toast.makeText(context, "Element id is required", Toast.LENGTH_SHORT).show()
            }
            else{
                PetDetail(elementId = animal.toInt())
            }
        }
    }


}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation() {

    val navController = rememberNavController()

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Settings,
        NavigationItem.PetScreen,
        NavigationItem.Profile
    )


    Scaffold(topBar = { TopAppBar(title = { Text(text = "Bottom Navigation VIew") }) },
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route


                items.forEach {
                    BottomNavigationItem(selected = currentRoute == it.route,
                        label = {
                            Text(
                                text = it.label,
                                color = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = it.icons, contentDescription = null,
                                tint = if (currentRoute == it.route) Color.DarkGray else Color.LightGray
                            )

                        },

                        onClick = {
                            if(currentRoute!=it.route){

                                navController.graph?.startDestinationRoute?.let {
                                    navController.popBackStack(it,true)
                                }

                                navController.navigate(it.route){
                                    launchSingleTop = true
                                }

                            }

                        })

                }


            }


        }) {

        NavigationController(navController = navController)

    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomNavigationJetpackComposeTheme {
        Greeting("Android")
    }
}