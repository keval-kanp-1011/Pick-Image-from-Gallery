package dev.kevalkanp.pickimagefromgallery

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dev.kevalkanp.pickimagefromgallery.ui.theme.PickImageFromGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PickImageFromGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    var selectedImage by remember {
                        mutableStateOf<Uri?>(null)
                    }
                    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
                        uri -> selectedImage = uri
                    }
                    PickImageFromGallery(selectedImage) {
                        launcher.launch("image/*")
                        //launcher.launch("image/png") for png type image
                    }
                }
            }
        }
    }
}

@Composable
private fun PickImageFromGallery(
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            if (selectedImage != null)
                Image(
                    painter = rememberImagePainter(selectedImage),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(400.dp)
                        .padding(20.dp)
                        /*.clickable {
                            onImageClick()
                        }*/)
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(onClick = onImageClick) {
                Text(text = "Pick Image")
            }
            /*else
                OutlinedButton(onClick = onImageClick) {
                    Text(text = "Pick Image")
                }*/
        }
    }
}