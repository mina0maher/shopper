package com.mina.shopper.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mina.domain.model.Product
import com.mina.shopper.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel=koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState()


    Scaffold {
        Surface (modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            when(uiState.value){
                is HomeScreenUIEvents.Error -> {
                    Text(text = (uiState.value as HomeScreenUIEvents.Error).message)
                }
                HomeScreenUIEvents.Loading -> {
                    CircularProgressIndicator()
                }
                is HomeScreenUIEvents.Success -> {
                    val data = (uiState.value as HomeScreenUIEvents.Success)
                    HomeContent(data.feature,data.popularProducts)
                }
            }
        }
    }



}

@Composable
fun ProfileHeader() {
    Box (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.size(50.dp))
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(text = "Hello,",
                style = MaterialTheme.typography.bodySmall)

                Text(text = "Mina Maher",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = 0.3f))
                    .padding(8.dp),
                contentScale = ContentScale.Inside
            )
        }
    }
}

@Composable
fun HomeContent(featured:List<Product>,popularProducts:List<Product>) {
    LazyColumn {
        item{
            ProfileHeader()
            Spacer(modifier = Modifier.size(16.dp))
        }
        item {
            if(featured.isNotEmpty()) {
                HomeProductRow(products = featured, title = "Featured")
            }
            if(popularProducts.isNotEmpty()) {
                Spacer(modifier = Modifier.size(16.dp))
                HomeProductRow(products = popularProducts, title = "Popular Products")
            }
        }
    }
}

@Composable
fun HomeProductRow(products:List<Product>,title:String) {
    Column {
        Box(modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()) {
            Text(text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterStart))
            Text(text = "View all",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterEnd))
        }
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow{
        items(products){product->
            ProductItem(product)
        }
        }
    }

}

@Composable
fun ProductItem(product: Product) {
        Card(modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(width = 126.dp, height = 144.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.3f))
        ){
            Column (modifier = Modifier.fillMaxSize()){
                AsyncImage(model =product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
 }