package com.example.whatsappclone.presentation.chatlistscreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.animelist.common.Constants
import com.example.whatsappclone.R
import com.example.whatsappclone.ui.theme.Green
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class
)
@Composable
fun chatListUIScreen(contacts:List<Triple<Long,String,String>>,navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                        Text(text = "WhatsApp")
                        },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Green
                    ),
                actions = {
                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.clip(CircleShape)
                    ) {
Icon(imageVector = Icons.Outlined.PhotoCamera,contentDescription = null)
                      }

                    IconButton(onClick = { /*TODO*/ }
                        ,modifier = Modifier.clip(CircleShape)
                    ) {
                        Icon(imageVector = Icons.Default.Search,contentDescription = null)
                    }

                    IconButton(onClick = { /*TODO*/ },
                        modifier = Modifier.clip(CircleShape)
                    ) {
                        Icon(imageVector = Icons.Default.MoreVert,contentDescription = null)
                    }
                }
                )
        },

    ) {
        Column(modifier = Modifier
            .padding()
            .fillMaxSize()
           ) {
            var selected by remember { mutableStateOf(0) }
            val titles = listOf("Chats", "Updates", "Calls")
            val pagerState = rememberPagerState {
                titles.size
            }
            val scope = rememberCoroutineScope()

            LaunchedEffect(pagerState.currentPage) {

                selected = pagerState.currentPage
            }
TabRow(selectedTabIndex =selected, containerColor = Green, indicator = {
        tabPositions ->
   if (selected < tabPositions.size) {
        TabRowDefaults.Indicator(
            modifier = Modifier.tabIndicatorOffset(tabPositions[selected]),
            color = Color.Black
        )
   }
} ) {
titles.forEachIndexed{
                     index,title->
    Tab(
        selected = selected == index,
        onClick = {
            scope.launch {
            pagerState.animateScrollToPage(index)
        }
           selected = index
                  },
        text = { Text(text = title,modifier = Modifier.padding(it), fontSize = 15.sp) },
        selectedContentColor = Color.White,

    )
}

}
HorizontalPager(state = pagerState,modifier = Modifier
    .fillMaxWidth()
    .weight(1f)) {index->
    if(index ==0){
     Column(modifier = Modifier
         .fillMaxSize()
         .verticalScroll(rememberScrollState()
         )
     ){

         contacts.forEach{
             Log.d("contactsList",it.second)
             chat(
                 name = it.second,
                 channelId = it.first.toString(),
                 navController = navController
             )
         }

     }

    }
    else if(index==1){
        Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "2nd screen")
        }
    }else{
        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
            Text(text = titles[index])
        }  
    }
        
    
}

        }

    }
}
/*            LaunchedEffect(selected){

//                pagerState.animateScrollToPage(selected)
            }
            LaunchedEffect(pagerState.currentPage,pagerState.isScrollInProgress){
                if(!pagerState.isScrollInProgress){
                    selected = pagerState.currentPage
                }
            }*/
@Composable
fun chat(name:String,channelId:String,navController: NavController) {
   Row (
       modifier = Modifier
       .fillMaxWidth()
       .background(Color.White)
       .height(IntrinsicSize.Min)
       .padding(12.dp)
       .clickable {
           navController.navigate(Constants.CHAT_ROUTE)
       }
   ){
Image(
    painter = painterResource(R.drawable.ic_launcher_background),
    contentDescription = null,
    modifier = Modifier
        .clip(CircleShape)
        .size(45.dp)
)
       Spacer(modifier = Modifier.width(15.dp))
       Column(verticalArrangement = Arrangement.SpaceBetween,modifier = Modifier.fillMaxHeight()) {
           Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom){
               Text(text = name, fontSize =20.sp)
               Text(text = "10:38 Am", fontSize = 12.sp)
           }
           Text(text = "chat")// fontSize = 25.sp,modifier = Modifier.fillMaxWidth()
       }
   }
}
@Preview
@Composable
fun s() {

}