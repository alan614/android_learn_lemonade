package com.teleserv.lemonader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teleserv.lemonader.ui.theme.LemonaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    LemonadeApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonaderTheme {
        Greeting("Android")
    }
}
*/

@Composable
fun LemonadeApp(modifier: Modifier)
{
    Lemonade(modifier = modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center))
}

@Composable
fun Lemonade(modifier: Modifier)
{
    var thisStep by remember {
        mutableIntStateOf(1)
    }

    val thisStepImageResource: Int = when(thisStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val thisStepStringResource: String = when(thisStep) {
        1 -> stringResource(R.string.lemon_step_1)
        2 -> stringResource(R.string.lemon_step_2)
        3 -> stringResource(R.string.lemon_step_3)
        else -> stringResource(R.string.lemon_step_4)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = thisStepImageResource),
            contentDescription = "Lemonade",
            modifier = Modifier.clickable {
                thisStep = clickNextStep(thisStep)
            }
                .background(color = Color(red = 0, green = 150, blue = 100, alpha = 80), shape = RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = thisStepStringResource,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview()
{
    LemonaderTheme {
        LemonadeApp(modifier = Modifier)
    }
}

fun clickNextStep(currentStep: Int): Int {
    if (currentStep == 2) {
        val squeezeOutcome = (1..10).random()
        if (squeezeOutcome > 6) {
            return 3
        }
        return 2
    } else if (currentStep < 4) {
        return currentStep + 1;
    } else {
        return 1
    }
}
